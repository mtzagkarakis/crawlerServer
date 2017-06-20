package gr.aueb.mscis.productCrawlerServer.crawler.parser;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import gr.aueb.mscis.productCrawlerServer.crawler.model.Product;
import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductPageUrl;
import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductUrl;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.PaginationSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductURLFromPaginationSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.utils.ParserUtils;
import gr.aueb.mscis.productCrawlerServer.utils.StringUtils;

public class GenericParser extends GenericHTMLDocumentParser{
	public List<ProductPageUrl> extractCategoryPageUrlsFromCategoryPageDocument(Document document, PaginationSelector paginationSelector) throws CannotParseDocumentException {
		if (document == null){
			throw new CannotParseDocumentException("Document is null");
		}
		List<String> allPagesUrlString = new ArrayList<>();
		
		try{
			Set<String> paginationUrls = 
					document
					.select(paginationSelector.getPaginatorSelector())
					.stream()
					.map(el->el.attr("href"))
					.filter(el -> UrlValidator.getInstance().isValid(el))
					.collect(Collectors.toSet());
			
			
			if (paginationUrls.size() == 1){
				allPagesUrlString.add(paginationUrls.iterator().next());
			} else {
					List<String> paginationUrlsSortedDesc =
						paginationUrls
						.stream()
						.sorted(new Comparator<String>() {
							@Override
							public int compare(String url0, String url1) {
								Integer off0 = ParserUtils.getUrlNumberParameter(url0, paginationSelector.getPaginationUrlParameter()).orElse(-1);
								Integer off1 = ParserUtils.getUrlNumberParameter(url1, paginationSelector.getPaginationUrlParameter()).orElse(-1);
								return off1.compareTo(off0);
							}
						})
						.collect(Collectors.toList());
					
					String lastPageUrl = paginationUrlsSortedDesc.get(0);
					String firstPageUrl = paginationUrlsSortedDesc.get(paginationUrlsSortedDesc.size()-1);

					int greaterOffset = ParserUtils.getUrlNumberParameter(lastPageUrl, paginationSelector.getPaginationUrlParameter()).orElse(-1);
					int smallestOffset = ParserUtils.getUrlNumberParameter(firstPageUrl, paginationSelector.getPaginationUrlParameter()).orElse(-1);
					
					//generate missing
					final int step = paginationSelector.getStep();
					for (int i=smallestOffset-step; i<=greaterOffset; i+=step){
						String urlToAdd = lastPageUrl.replace(paginationSelector.getPaginationUrlParameter()+"="+greaterOffset, paginationSelector.getPaginationUrlParameter()+"="+i);
						allPagesUrlString.add(urlToAdd);
					}
			}
		} catch (Exception e) {
			throw new CannotParseDocumentException("Cannot detect last pagination url " + e.getMessage(), e);
		}
		
		
		return ProductPageUrl.fromStringList(allPagesUrlString);
	}
	
	public List<ProductUrl> extractProductUrlFromCategoryPageDocument(Document document, ProductURLFromPaginationSelector productItemSelector) throws CannotParseDocumentException{
		if (document == null){
			throw new CannotParseDocumentException("Document is null");
		}
		
		List<String> productUrlsStrings = new ArrayList<>();
		try{
			productUrlsStrings =
				document
				.select(productItemSelector.getSelector())
				.stream()
				.map(el->el.attr("href"))
				.collect(Collectors.toSet())
				.stream()
				.collect(Collectors.toList());
			
		} catch (Exception e) {
			throw new CannotParseDocumentException("Cannot extract element " + e.getMessage(), e);
		}
		
		return ProductUrl.fromStringList(productUrlsStrings);
	}
	
	public Product extractProductFromDocument(Document document, String productUrl, ProductSelector productSelector) throws CannotParseDocumentException{
		Product.Builder productBuilder = new Product.Builder();
		try{	
			if (StringUtils.stringIsEmptyOrNull(productSelector.getProductAttributeKeySelector()) == false &&
					StringUtils.stringIsEmptyOrNull(productSelector.getProductAttributeValueSelector()) == false){
				List<String> attributesKeys = 
						document
							.select(productSelector.getProductAttributeKeySelector())
							.stream()
							.map(el->el.text().trim())
							.map(str->str.replace(":", ""))
							.collect(Collectors.toList());
					
				List<String> attributesValues = 
							document
								.select(productSelector.getProductAttributeValueSelector())
								.stream()
								.map(el->el.text().trim())
								.collect(Collectors.toList());
				
				if (attributesKeys.size() != attributesValues.size())
					throw new CannotParseDocumentException("Attribute keys and attribute values lists does not have the same length in product Url: " + productUrl);
					
				Map<String, String> attributes = new HashMap<>();
				for (int i=0; i<attributesKeys.size(); i++){
					if (attributes.containsKey(attributesKeys.get(i)) == false){
						attributes.put(attributesKeys.get(i), attributesValues.get(i));
					}
				}	
				productBuilder.setAttributes(attributes);
			}
			
			if (StringUtils.stringIsEmptyOrNull(productSelector.getProductManufacturerSelector()) == false){
				Optional<Element> manufacturerElementOpt = document.select(productSelector.getProductManufacturerSelector()).stream().findFirst();
				if (manufacturerElementOpt.isPresent())
					productBuilder.setManufacturer(manufacturerElementOpt.get().text().trim());
			}
			if (StringUtils.stringIsEmptyOrNull(productSelector.getProductNameSelector()) == false){
				Optional<Element> nameElementOpt = document.select(productSelector.getProductNameSelector()).stream().findFirst();
				if (nameElementOpt.isPresent())
					productBuilder.setName(nameElementOpt.get().text().trim());	
			}
			if (StringUtils.stringIsEmptyOrNull(productSelector.getProductPriceSelector())== false){
				Optional<Element> priceElementOpt = document.select(productSelector.getProductPriceSelector()).stream().findFirst();
				if (priceElementOpt.isPresent()){
					String priceStr = priceElementOpt.get().text();
					priceStr = priceStr.replace("€", "");
					priceStr = priceStr.replace("$", "");
					if (productSelector.getProductPriceLocale() != Locale.US){
						priceStr = priceStr.replace(".", "");
					}
					productBuilder.setPrice(NumberFormat.getNumberInstance(productSelector.getProductPriceLocale()).parse(priceStr.trim()).doubleValue());
				}
			}
			
			if (StringUtils.stringIsEmptyOrNull(productSelector.getProductImageSelector()) == false){
				Optional<Element> imageElement = document.select(productSelector.getProductImageSelector()).stream().findAny();
				if (imageElement.isPresent())
					productBuilder.setImageUrl(imageElement.get().attr("href"));
			}
			return productBuilder.setUrl(productUrl).build();
		} catch (Exception e) {
			throw new CannotParseDocumentException("Cannot extract product from Page", e);
		}
	}
	
}
