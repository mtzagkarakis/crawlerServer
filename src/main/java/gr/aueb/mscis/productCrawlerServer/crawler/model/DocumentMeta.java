package gr.aueb.mscis.productCrawlerServer.crawler.model;

import org.jsoup.nodes.Document;

public class DocumentMeta {
	private final Document document;
	private final String url, encoding;
	public DocumentMeta(Document document, String url, String encoding) {
		super();
		this.document = document;
		this.url = url;
		this.encoding = encoding;
	}
	public Document getDocument() {
		return document;
	}
	public String getUrl() {
		return url;
	}
	public String getEncoding() {
		return encoding;
	}
	
	
	
}
