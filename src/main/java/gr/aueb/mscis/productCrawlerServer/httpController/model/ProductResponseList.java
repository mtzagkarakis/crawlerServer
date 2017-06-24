package gr.aueb.mscis.productCrawlerServer.httpController.model;

import java.util.List;

public class ProductResponseList {
	private final List<ProductResponse> matchedProductsList;
	private final long downloadAndParseTime;
	private final long entityResolutionTime;
	public ProductResponseList(List<ProductResponse> matchedProductsList, long downloadAndParseTime,
			long entityResolutionTime) {
		super();
		this.matchedProductsList = matchedProductsList;
		this.downloadAndParseTime = downloadAndParseTime;
		this.entityResolutionTime = entityResolutionTime;
	}
	public List<ProductResponse> getMatchedProductsList() {
		return matchedProductsList;
	}
	public long getDownloadAndParseTime() {
		return downloadAndParseTime;
	}
	public long getEntityResolutionTime() {
		return entityResolutionTime;
	}
	
}
