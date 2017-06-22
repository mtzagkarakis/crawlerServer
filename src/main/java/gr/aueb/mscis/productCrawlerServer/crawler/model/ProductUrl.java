package gr.aueb.mscis.productCrawlerServer.crawler.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

public class ProductUrl {
	private final static Logger logger = Logger.getLogger(ProductUrl.class.getName());
	private final URL url;
	public ProductUrl(URL url){
		Objects.requireNonNull(url);
		this.url = url;
	}
	public URL getUrl() {
		return url;
	}
	@Override
	public String toString() {
		return "ProductUrl [url=" + url + "]";
	}
	public static List<ProductUrl> fromStringList(List<String> urlStrList){
		List<ProductUrl> productPages = new ArrayList<>();
		for(String urlStr: urlStrList){
			try {
				productPages.add(new ProductUrl(new URL(urlStr)));
			} catch (MalformedURLException e) {
				logger.warn("malformedUrl: " + (urlStr.isEmpty()?"(empty)":urlStr) + " exception message: " + e.getMessage(), e);
			}	
		}
		return productPages;
	}
}
