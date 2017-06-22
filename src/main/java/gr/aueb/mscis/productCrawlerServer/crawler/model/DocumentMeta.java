package gr.aueb.mscis.productCrawlerServer.crawler.model;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.nodes.Document;

public class DocumentMeta {
	private final Document document;
	private final URL url;
	private final String encoding;
	public DocumentMeta(Document document, String url, String encoding) throws MalformedURLException {
		super();
		this.document = document;
		this.url = new URL(url);
		this.encoding = encoding;
	}
	public DocumentMeta(Document document, URL url, String encoding){
		super();
		this.document = document;
		this.url = url;
		this.encoding = encoding;
	}
	public Document getDocument() {
		return document;
	}
	public URL getUrl() {
		return url;
	}
	public String getUrlAsString(){
		return url.toString();
	}
	public String getEncoding() {
		return encoding;
	}
	
	
	
}
