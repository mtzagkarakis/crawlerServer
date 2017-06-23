package gr.aueb.mscis.productCrawlerServer;

import org.jsoup.nodes.Document;
import org.junit.Test;

import gr.aueb.mscis.productCrawlerServer.crawler.fetcher.UrlDocumentDownloader;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotDownloadDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;
import gr.aueb.mscis.productCrawlerServer.utils.FileUtils;

public class Generic {
	private int counter;
	@Test
	public void p() throws CannotDownloadDocumentException, CannotParseDocumentException{
		UrlDocumentDownloader down = new UrlDocumentDownloader();
		Document doc = down.downloadDocument("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm", "utf-8");
		System.out.println(doc.toString());
	}
	

}
