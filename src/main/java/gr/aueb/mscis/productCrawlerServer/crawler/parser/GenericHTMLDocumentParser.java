package gr.aueb.mscis.productCrawlerServer.crawler.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;

public class GenericHTMLDocumentParser {
	public Document parseDocumentFromString(String documentString) throws CannotParseDocumentException{
		try{
			return Jsoup.parse(documentString, "utf-8");
		} catch (Throwable e){
			throw new CannotParseDocumentException("Cannot parse document from String", e);
		}
	}
}
