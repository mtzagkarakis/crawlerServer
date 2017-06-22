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


public class PlaisioProductParserTest {
	private URL getURL(){
		try {
			return new URL("http://www.google.com");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	ProductSelector ps = new ProductSelector()
			.setProductAttributeKeySelector("li.characteristicsItem span.characteristicLabel")
			.setProductAttributeValueSelector("li.characteristicsItem span.characteristicValue")
			.setProductNameSelector("span.topAreaProductTitle")
			.setProductPriceSelector("span.productPrice", Locale.FRANCE)
			.setProductManufacturerSelector("[itemprop='manufacturer']")
			.setProductImageSelector("#productImagesLinksLarge a");
	
	@Test
	public void parseProductPageTest() throws CannotParseDocumentException{	
		Document doc = Jsoup.parse(FileUtils.fileToString("testFiles/plaisio/plaisio_product_page.html").get());
		
		Product product = (new GenericParser()).extractProductFromDocument(doc, getURL(), ps);
		
		Assert.assertEquals("Samsung Galaxy S8+ 4G+ Smartphone Μαύρο", product.getName());
		Assert.assertEquals("929", product.getPrice());
		Assert.assertEquals(34, product.getAttributes().size());
		Assert.assertEquals("//www.plaisio-cdn.gr/ProductImages/1000x1000/2651300.JPG", product.getImageUrl());
		Assert.assertEquals("url",  product.getUrl());
		
		Assert.assertEquals("4G+", product.getAttributes().get("Δίκτυο"));
		Assert.assertEquals("3500 mAh", product.getAttributes().get("Χωρητικότητα Μπαταρίας"));
		Assert.assertEquals("Οδηγός γρήγορης έναρξης / Φορτιστής", product.getAttributes().get("Περιλαμβάνονται στη συσκευασία"));

	}
	
	@Test
	public void parseProductPage1Test() throws CannotParseDocumentException{	
		Document doc = Jsoup.parse(FileUtils.fileToString("testFiles/plaisio/plaisio_product_page1.html").get());
		
		Product product = (new GenericParser()).extractProductFromDocument(doc, getURL(), ps);
		
		Assert.assertEquals("Apple iPhone 7 Plus 128GB 4G+ Smartphone Jet Black", product.getName());
		Assert.assertEquals("1079", product.getPrice());
		Assert.assertEquals(35, product.getAttributes().size());
		Assert.assertEquals("//www.plaisio-cdn.gr/ProductImages/1000x1000/2569809.JPG", product.getImageUrl());
		Assert.assertEquals("url",  product.getUrl());
		
		Assert.assertEquals("4G+", product.getAttributes().get("Δίκτυο"));
		Assert.assertEquals("2900 mAh", product.getAttributes().get("Χωρητικότητα Μπαταρίας"));
		Assert.assertEquals("Ακουστικά / Οδηγός γρήγορης έναρξης / Φορτιστής", product.getAttributes().get("Περιλαμβάνονται στη συσκευασία"));

	}
}
