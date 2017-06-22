package gr.aueb.mscis.productCrawlerServer.parsers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.productCrawlerServer.crawler.model.Product;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.GenericParser;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;
import gr.aueb.mscis.productCrawlerServer.utils.FileUtils;

public class EshopProductParserTest {
	private URL getURL(){
		try {
			return new URL("http://www.google.com");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void parseProductPageTest() throws CannotParseDocumentException{	
		Document doc = Jsoup.parse(FileUtils.fileToString("testFiles/eshop/eshop_product_page.html").get());
		
		ProductSelector ps = new ProductSelector()
				.setProductAttributeKeySelector("#mobile_desc table  tr td.details2")
				.setProductAttributeValueSelector("#mobile_desc table  tr td.details1")
				.setProductNameSelector("[itemprop='name']")
				.setProductPriceSelector("[itemprop='price']", Locale.US)
				.setProductManufacturerSelector("[itemprop='manufacturer']")
				.setProductImageSelector("td.web-main-photo-box a");
		Product product = (new GenericParser()).extractProductFromDocument(doc, getURL(), ps);
		
		
		Assert.assertEquals("url", product.getUrl());
		Assert.assertEquals("ΚΙΝΗΤΟ APPLE IPHONE 7 PLUS 128GB RED SPECIAL EDITION", product.getName());
		Assert.assertEquals("APPLE", product.getManufacturer());
		Assert.assertEquals("http://images.e-shop.gr/images/TEL/BIG/TEL.004857.jpg", product.getImageUrl());
		Assert.assertEquals("977.00", product.getPrice());
		Assert.assertEquals(24, product.getAttributes().size());
		
		Assert.assertEquals("GSM, HSPA, LTE", product.getAttributes().get("Mobile Internet"));
		Assert.assertEquals("5.5'', LED-backlit, πολλαπλής αφής, 1920 x 1080", product.getAttributes().get("Οθόνη"));

	}

}
