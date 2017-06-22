package gr.aueb.mscis.productCrawlerServer.crawler.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

public class ProductPageUrl {
	private static final Logger logger = Logger.getLogger(ProductPageUrl.class.getName());
	private final URL url;
	public ProductPageUrl(URL url) {
		Objects.requireNonNull(url);
		this.url = url;
	}
	

	public URL getUrl() {
		return url;
	}


	@Override
	public String toString() {
		return "ProductPageUrl [url=" + url + "]";
	}
	
	public static List<ProductPageUrl> fromStringList(List<String> urlStrList){
		List<ProductPageUrl> productPages = new ArrayList<>();
		for(String urlStr: urlStrList){
			try {
				productPages.add(new ProductPageUrl(new URL(urlStr)));
			} catch (MalformedURLException e) {
				logger.warn("malformedUrl: " + (urlStr.isEmpty()?"(empty)":urlStr) + " exception message: " + e.getMessage(), e);
			}	
		}
		return productPages;
	}
	
}
