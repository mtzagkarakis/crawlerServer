package gr.aueb.mscis.productCrawlerServer.crawler.model;

import java.net.URL;

import org.jsoup.nodes.Document;

import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.AbstractSelector;

public class DocumentMetaWithSelector<T extends AbstractSelector> {
	private final DocumentMeta documentMeta;
	private final T data;
	public DocumentMetaWithSelector(DocumentMeta documentMeta, T data) {
		super();
		this.documentMeta = documentMeta;
		this.data = data;
	}
	public DocumentMeta getDocumentMeta() {
		return documentMeta;
	}
	public Document getDocument(){
		return documentMeta.getDocument();
	}
	public URL getUrl(){
		return documentMeta.getUrl();
	}
	public String getUrlAsString(){
		return documentMeta.getUrl().toString();
	}
	public String getEncoding(){
		return documentMeta.getEncoding();
	}
	public T getData() {
		return data;
	}
}
