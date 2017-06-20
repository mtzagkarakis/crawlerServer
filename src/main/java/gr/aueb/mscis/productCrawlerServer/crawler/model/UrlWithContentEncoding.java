package gr.aueb.mscis.productCrawlerServer.crawler.model;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlWithContentEncoding {
	private final URL url;
	private final String encoding;
	public UrlWithContentEncoding(String urlStr, String encoding) throws MalformedURLException {
		super();
		this.url = new URL(urlStr);		
		this.encoding = encoding;
	}
	public UrlWithContentEncoding(URL url, String encoding){
		super();
		this.url = url;
		this.encoding = encoding;
	}
	public String getUrlAsString() {
		return url.toString();
	}
	public URL getUrl(){
		return url;
	}
	public String getUrlHost(){
		return url.getHost();
	}
	public String getEncoding() {
		return encoding;
	}
	
	
	
	
}
