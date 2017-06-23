package gr.aueb.mscis.productCrawlerServer.crawler.parser;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.log4j.Logger;
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
	private final static Logger logger = Logger.getLogger(GenericParser.class.getName());
	private boolean isRelativeURL(URL base, String test){
		if (test.startsWith(base.getProtocol()) || test.startsWith(base.getHost()) )
			return false;
		return true;
	}
	public List<ProductPageUrl> extractCategoryPageUrlsFromCategoryPageDocument(Document document, URL baseUrl, PaginationSelector paginationSelector) throws CannotParseDocumentException {
		if (document == null){
			throw new CannotParseDocumentException("Document is null");
		}
		if (baseUrl == null){
			throw new CannotParseDocumentException("BaseUrl is null");
		}
		if (StringUtils.stringIsEmptyOrNull(paginationSelector.getPaginationUrlParameter())
				||
				StringUtils.stringIsEmptyOrNull(paginationSelector.getPaginatorSelector())){
			return ProductPageUrl.fromStringList(Arrays.asList(baseUrl.toString()));
		}
		List<String> allPagesUrlString = new ArrayList<>();
		
		try{
			Set<String> paginationUrls = 
					document
					.select(paginationSelector.getPaginatorSelector())
					.stream()
					.map(el->el.attr("href"))
					.map(el -> {
						if (isRelativeURL(baseUrl, el)){
							el = baseUrl.getProtocol() + "://" + baseUrl.getHost() + (el.startsWith("/")?el:"/"+el);
						}
						return el;
					})
					.filter(el -> UrlValidator.getInstance().isValid(el))
					.collect(Collectors.toSet());
			
			if (paginationUrls.size() == 1){
				allPagesUrlString.add(paginationUrls.iterator().next());
			} else {
					List<String> paginationUrlsSortedDesc =
						paginationUrls
						.stream()
						.filter(url -> ParserUtils.getUrlParameter(url, paginationSelector.getPaginationUrlParameter()).isPresent())
						.sorted((url0, url1)->
							ParserUtils.getUrlNumberParameter(url1, paginationSelector.getPaginationUrlParameter()).orElse(-1)
							.compareTo(ParserUtils.getUrlNumberParameter(url0, paginationSelector.getPaginationUrlParameter()).orElse(-1))
						)
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
	
	public List<ProductUrl> extractProductUrlFromCategoryPageDocument(Document document, URL baseUrl, ProductURLFromPaginationSelector productItemSelector) throws CannotParseDocumentException{
		if (document == null){
			throw new CannotParseDocumentException("Document is null");
		}
		if (baseUrl == null){
			throw new CannotParseDocumentException("BaseUrl is null");
		}
		List<String> productUrlsStrings = new ArrayList<>();
		try{
			productUrlsStrings =
				document
				.select(productItemSelector.getSelector())
				.stream()
				.map(el->el.attr("href"))
				.map(el -> {
					if (isRelativeURL(baseUrl, el)){
						el = baseUrl.getProtocol() + "://" + baseUrl.getHost() + (el.startsWith("/")?el:"/"+el);
					}
					return el;
				})
				.filter(el -> UrlValidator.getInstance().isValid(el))
				.distinct()
				.collect(Collectors.toList());

		} catch (Exception e) {
			throw new CannotParseDocumentException("Cannot extract element " + e.getMessage(), e);
		}
		
		return ProductUrl.fromStringList(productUrlsStrings);
	}
	
	public Product extractProductFromDocument(Document document, URL productUrl, ProductSelector productSelector) throws CannotParseDocumentException{
		Product.Builder productBuilder = new Product.Builder();
		try{	
			if (!StringUtils.stringIsEmptyOrNull(productSelector.getProductAttributeKeySelector()) 
					&&
				!StringUtils.stringIsEmptyOrNull(productSelector.getProductAttributeValueSelector())){
				
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
				
				if (attributesKeys.size() != attributesValues.size()){
					logger.info("attributes: " + attributesKeys);
					logger.info("values: " + attributesValues);
					throw new CannotParseDocumentException("Attribute keys " + attributesKeys.size() + " and attribute values " + attributesValues.size() + " lists does not have the same length in product Url: " + productUrl);
				}
					
				Map<String, String> attributes = new HashMap<>();
				for (int i=0; i<attributesKeys.size(); i++){
					if (!attributes.containsKey(attributesKeys.get(i))){
						attributes.put(attributesKeys.get(i), attributesValues.get(i));
					}
				}	
				productBuilder.setAttributes(attributes);
			}
			
			if (!StringUtils.stringIsEmptyOrNull(productSelector.getProductManufacturerSelector())){
				Optional<Element> manufacturerElementOpt = document.select(productSelector.getProductManufacturerSelector()).stream().findFirst();
				if (manufacturerElementOpt.isPresent())
					productBuilder.setManufacturer(manufacturerElementOpt.get().text().trim());
			}
			if (!StringUtils.stringIsEmptyOrNull(productSelector.getProductNameSelector())){
				Optional<Element> nameElementOpt = document.select(productSelector.getProductNameSelector()).stream().findFirst();
				if (nameElementOpt.isPresent())
					productBuilder.setName(nameElementOpt.get().text().trim());	
			}
			if (!StringUtils.stringIsEmptyOrNull(productSelector.getProductPriceSelector())){
				Optional<Element> priceElementOpt = document.select(productSelector.getProductPriceSelector()).stream().findFirst();
				if (priceElementOpt.isPresent()){
					String priceStr = priceElementOpt.get().text();
					if (!productSelector.getProductPriceLocale().equals(Locale.US)){
						priceStr = priceStr.replace(".", "");
						priceStr = priceStr.replace(",", ".");
					}
					priceStr = priceStr.replaceAll("[^\\d.]", "");
					productBuilder.setPrice(priceStr);
				}
			}
			
			if (!StringUtils.stringIsEmptyOrNull(productSelector.getProductImageSelector())){
				
				Optional<Element> imageElement = document.select(productSelector.getProductImageSelector()).stream().findAny();
				if (imageElement.isPresent()){
					String possibleImage = imageElement.get().attr("href");
					if (possibleImage.length() > 0){
						if (isRelativeURL(productUrl, possibleImage))
							possibleImage = productUrl.getProtocol() + "://" + productUrl.getHost() + (possibleImage.startsWith("/")?possibleImage:"/"+possibleImage);
						productBuilder.setImageUrl(possibleImage);
					} else{
						possibleImage = imageElement.get().attr("src");
						if (possibleImage.length() > 0 && isRelativeURL(productUrl, possibleImage))
							possibleImage = productUrl.getProtocol() + "://" + productUrl.getHost() + (possibleImage.startsWith("/")?possibleImage:"/"+possibleImage);
						productBuilder.setImageUrl(possibleImage);
					}
				}
			}
			return productBuilder.setUrl(productUrl.toString()).build();
		} catch (Exception e) {
			throw new CannotParseDocumentException("Cannot extract product from Page", e);
		}
	}
	
}
