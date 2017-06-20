package gr.aueb.mscis.productCrawlerServer.crawler.model.selectors;

public class ProductURLFromPaginationSelector extends AbstractSelector{
	private final String selector;

	public ProductURLFromPaginationSelector(String selector) {
		super();
		this.selector = selector;
	}

	public String getSelector() {
		return selector;
	}
	
}
