package gr.aueb.mscis.productCrawlerServer.crawler.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;

public class ProductPageUrl {
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
	
	public static List<ProductPageUrl> fromStringList(List<String> urlStrList) throws CannotParseDocumentException{
		List<ProductPageUrl> productPages = new ArrayList<>();
		for(String urlStr: urlStrList){
			try {
				productPages.add(new ProductPageUrl(new URL(urlStr)));
			} catch (MalformedURLException e) {
				throw new CannotParseDocumentException(e.getMessage(), e);
			}	
		}
		return productPages;
	}
	
}
