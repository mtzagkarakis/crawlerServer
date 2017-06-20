package gr.aueb.mscis.productCrawlerServer.httpController.model;

import java.util.Set;

import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductSchema;

public class ProductResponse {
	private final Set<ProductSchema> productSet;

	public ProductResponse(Set<ProductSchema> productSet) {
		super();
		this.productSet = productSet;
	}

	public Set<ProductSchema> getProductSet() {
		return productSet;
	}
	
}
