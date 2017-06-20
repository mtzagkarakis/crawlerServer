package gr.aueb.mscis.productCrawlerServer.parsers;

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




public class PlaisioCategoryParserTest {
	private GenericParser genericParser = new GenericParser();
	@Test
	public void parsePagesTest() throws CannotParseDocumentException{
		Document document = genericParser.parseDocumentFromString(FileUtils.fileToString("testFiles/plaisio/product_smartphones_category_page.html").get());
		List<ProductPageUrl> productPages = genericParser.extractCategoryPageUrlsFromCategoryPageDocument(document, new PaginationSelector("#ctl00_BaseLayoutContentArea_pagingBottom_divPager a", "page", 1));
		
		Assert.assertEquals(13, productPages.size());
		
		Set<String> collectedUrlsSet = productPages.stream().map(pp->pp.getUrl().toString()).collect(Collectors.toCollection(HashSet::new));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=1"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=2"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=3"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=4"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=5"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=6"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=7"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=8"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=9"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=10"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=11"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=12"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm?page=13"));
	}

	@Test
	public void parseProductUrlsTest() throws CannotParseDocumentException{
		Document document = genericParser.parseDocumentFromString(FileUtils.fileToString("testFiles/plaisio/product_smartphones_category_page.html").get());
		List<ProductUrl> productUrls = genericParser.extractProductUrlFromCategoryPageDocument(document, new ProductURLFromPaginationSelector("div.productTopWrap div.productDetailsWrap div.productTitleDescrWrap div.productTitle a"));
		
		Assert.assertEquals(20, productUrls.size());
		Set<String> collectedUrlsSet = productUrls.stream().map(ProductUrl::getUrl).map(URL::toString).collect(Collectors.toCollection(HashSet::new));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Galaxy-S8plus-64GB-Mauro-SM-G955FZKAEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Galaxy-S8p-64GB-Orchid-Gray-SM-G955FZVAEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Galaxy-J5-2016-Xriso-SM-J510FZDNEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Smartphone-Galaxy-S7-Edge-Mavro-32GB.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Smartphone-Galaxy-J7-2016-Xriso-SM-J710FZDNEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Galaxy-J5-2016-Mauro-SM-J510FZKNEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Smartphone-Galaxy-J5-2016-Dual-Sim-Mauro-SM-J510FZKUEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Huawei-P8-Lite-Mauro-51094698.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Galaxy-J5-2016-Leuko-SM-J510FZWNEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Smartphone-Galaxy-J7-2016-Mauro-SM-J710FZKNEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Smartphone-Galaxy-S7-Mavro-32GB.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Smartphone-Galaxy-S7-Edge-Xriso-32GB.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Smartphone-Galaxy-J5-2016-Dual-Sim-Leuko-SM-J510FZWUEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Turbo-X-Smartphone-O-4G-Gold.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Turbo-X-Smartphone-A2-Black-Android-6-0-%ce%9cB-500-6-0-B.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Turbo-X-Smartphone-O-4G-Black.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Samsung-Galaxy-S7-Edge-Mple-32GB-SM-G935FZBAEUR.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Xiaomi-Redmi-Note-4X-32G-Mauro-348069.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Xiaomi-Redmi-4A-32GB-Gri-347193.htm"));
		Assert.assertTrue(collectedUrlsSet.contains("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones/Turbo-X-Smartphone-z-mauro.htm"));
	}
}
