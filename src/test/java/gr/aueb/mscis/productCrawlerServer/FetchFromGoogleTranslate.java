package gr.aueb.mscis.productCrawlerServer;

import java.util.Random;

import org.jsoup.nodes.Document;
import org.junit.Test;

import gr.aueb.mscis.productCrawlerServer.crawler.fetcher.UrlDocumentDownloader;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotDownloadDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;

public class FetchFromGoogleTranslate {
	private UrlDocumentDownloader urlDocumentDownloader = new UrlDocumentDownloader();
	private final String url = 
			"https://translate.google.com/translate?sl=el&tl=en&js=y&prev=_t&hl=en&ie=UTF-8&u=http%3A%2F%2Fwww.skroutz.gr%2Fc%2F40%2Fkinhta-thlefwna.html%3Fkeyphrase%3Dsamsung%2Bgalaxy%2Bs7&edit-text=&act=url";
	
	private void downloadFromGoogleTranslate() throws CannotDownloadDocumentException, CannotParseDocumentException{
		Document doc;
		try {
			doc = urlDocumentDownloader.downloadDocument(url, "utf-8");
			doc = urlDocumentDownloader.downloadDocument(doc.getElementsByTag("iframe").first().attr("src"), "utf-8");
			doc = urlDocumentDownloader.downloadDocument(doc.getElementsByTag("a").first().attr("href"), "utf-8");
		} catch (RuntimeException e) {
			throw new CannotParseDocumentException(e);
		}
		
//		FileUtils.writeToFile("google_translate.html", doc.toString());
	}
	
	@Test
	public void loop() throws InterruptedException{
		int success = 0;
		int failures = 0;
		for(int i=0; i<10_000;i++){
			System.out.println(i);
			try {
				downloadFromGoogleTranslate();
				success++;
			} catch (CannotDownloadDocumentException | CannotParseDocumentException e) {
				failures++;
			}
			Random rand = new Random();
			Thread.sleep(rand.nextInt(750)+400);
		}
		System.out.println("success: " + success + " failures: " + failures);
		
	}
}
