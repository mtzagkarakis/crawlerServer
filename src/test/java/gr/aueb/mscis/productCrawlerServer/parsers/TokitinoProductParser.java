package gr.aueb.mscis.productCrawlerServer.parsers;

import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import gr.aueb.mscis.productCrawlerServer.crawler.model.Product;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.GenericParser;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;
import gr.aueb.mscis.productCrawlerServer.utils.FileUtils;

public class TokitinoProductParser {
	@Test
	public void parseProductPageTest() throws CannotParseDocumentException{	
		Document doc = Jsoup.parse(FileUtils.fileToString("testFiles/tokinito/tokinito_product_page.html").get());
		
		ProductSelector ps = new ProductSelector()
				.setProductAttributeKeySelector(".box-additional table.data-table tr th")
				.setProductAttributeValueSelector(".box-additional table.data-table tr td.data")
				.setProductNameSelector("h2.product-name")
				.setProductPriceSelector(".regular-price .price", Locale.FRANCE)
				.setProductManufacturerSelector("")
				.setProductImageSelector("#yt_cloudzoom");
		Product product = (new GenericParser()).extractProductFromDocument(doc, "url", ps);
		
		System.out.println(product);
	}
}
