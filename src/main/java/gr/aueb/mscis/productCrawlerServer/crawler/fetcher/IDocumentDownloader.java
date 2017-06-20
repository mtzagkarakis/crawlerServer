package gr.aueb.mscis.productCrawlerServer.crawler.fetcher;

import org.jsoup.nodes.Document;

import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotDownloadDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;

public interface IDocumentDownloader {
	Document downloadDocument(String urlStr, String charset) throws CannotDownloadDocumentException, CannotParseDocumentException;
}
