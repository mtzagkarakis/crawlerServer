package gr.aueb.mscis.productCrawlerServer.parsers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductPageUrl;
import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductUrl;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.PaginationSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductURLFromPaginationSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.GenericParser;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;
import gr.aueb.mscis.productCrawlerServer.utils.FileUtils;

public class EshopCategoryParserTest {
	private GenericParser genericParser = new GenericParser();
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
	public void parsePagesTest() throws CannotParseDocumentException{		
		Document document = genericParser.parseDocumentFromString(FileUtils.fileToString("testFiles/eshop/product_smartphones_category_page.html").get());
		
		List<ProductPageUrl> productPages = genericParser.extractCategoryPageUrlsFromCategoryPageDocument(document, getURL(),new PaginationSelector("a.mobile_list_navigation_link", "offset", 10));

		Assert.assertEquals(52, productPages.size());
		Set<String> collectedUrlsSet = productPages.stream().map(pp->pp.getUrl().toString()).collect(Collectors.toCollection(HashSet::new));
		
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?offset=0&table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?offset=10&table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?offset=20&table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1"));
		//...
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?offset=490&table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?offset=500&table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?offset=510&table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1"));
		
	}
	
	@Test
	public void parseProductUrlsTest() throws CannotParseDocumentException{
		Document document = genericParser.parseDocumentFromString(FileUtils.fileToString("testFiles/eshop/product_smartphones_category_page.html").get());
		List<ProductUrl> productUrls = genericParser.extractProductUrlFromCategoryPageDocument(document, getURL(), new ProductURLFromPaginationSelector("a.web-title-link"));
		
		Assert.assertEquals(10, productUrls.size());
		Set<String> collectedUrlsSet = productUrls.stream().map(ProductUrl::getUrl).map(URL::toString).collect(Collectors.toCollection(HashSet::new));
		
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-vodafone-858-smart-black-gr-p-TEL.090760"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-alcatel-pixi-4-4-4034d-dual-sim-black-gr-p-TEL.090454"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-alcatel-pixi-4-4-4034d-dual-sim-white-gr-p-TEL.090455"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-zte-blade-l110-dual-sim-black-gr-p-TEL.090208"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-tp-link-neffos-y5l-3gdual-sim-8gb-dark-grey-p-TEL.090650"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-tp-link-neffos-y5l-3g-dual-sim-8gb-pearl-white-p-TEL.090649"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-lenovo-a2010-4g-dual-sim-black-gr-p-TEL.090027"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-zte-blade-l5-dual-sim-white-gr-p-TEL.090356"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-alcatel-5010d-pixi-4-5-dual-sim-volcano-black-gr-p-TEL.090294"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.e-shop.gr/kinito-alcatel-5010d-pixi-4-5-dual-sim-white-gr-p-TEL.090295"));
	}
}
