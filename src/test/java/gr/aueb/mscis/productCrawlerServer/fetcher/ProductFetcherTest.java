package gr.aueb.mscis.productCrawlerServer.fetcher;

public class ProductFetcherTest {
	/*@Test
	public void fetchPlaisioProductsAsyncTest(){
		ProductFetcher productFetcher = new ProductFetcher(20);
		PlaisioParser plaisioParser = new PlaisioParser();
		List<Product> products = new ArrayList<>();
		try {
			products = productFetcher.fetchProductsAsync("http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm", new UrlDocumentDownloader("utf-8"), plaisioParser, plaisioParser);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(products.size());
	}*/
	
	/*@Test
	public void fetchEshopProductsAsyncTest(){
		ProductFetcher productFetcher = new ProductFetcher(1);
		EshopParser eshopParser = new EshopParser();
		List<Product> products = new ArrayList<>();
		try {
			products = productFetcher.fetchProductsAsync("http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1", new UrlDocumentDownloader("iso-8859-7"), eshopParser);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(products.size());
	}*/
}
