package gr.aueb.mscis.productCrawlerServer.crawler.model.selectors;

public class PaginationSelector extends AbstractSelector{
	private final String paginatorSelector, paginationUrlParameter;
	private final int step;
	public PaginationSelector(String paginatorSelector, String paginationUrlParameter, int step) {
		super();
		this.paginatorSelector = paginatorSelector;
		this.paginationUrlParameter = paginationUrlParameter;
		this.step = step;
	}
	public String getPaginatorSelector() {
		return paginatorSelector;
	}
	public String getPaginationUrlParameter() {
		return paginationUrlParameter;
	}
	public int getStep() {
		return step;
	}
	@Override
	public String toString() {
		return "PaginationSelector [paginatorSelector=" + paginatorSelector + ", paginationUrlParameter="
				+ paginationUrlParameter + ", step=" + step + "]";
	}
	
	
	
	
}
