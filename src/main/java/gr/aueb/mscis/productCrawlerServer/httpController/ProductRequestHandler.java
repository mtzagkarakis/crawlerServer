package gr.aueb.mscis.productCrawlerServer.httpController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import gr.aueb.mscis.productCrawlerServer.crawler.fetcher.IDocumentDownloader;
import gr.aueb.mscis.productCrawlerServer.crawler.fetcher.ProductFetcher;
import gr.aueb.mscis.productCrawlerServer.crawler.matcher.SchemaMatcher;
import gr.aueb.mscis.productCrawlerServer.crawler.model.DocumentMeta;
import gr.aueb.mscis.productCrawlerServer.crawler.model.Product;
import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductSchema;
import gr.aueb.mscis.productCrawlerServer.crawler.model.UrlWithContentEncoding;
import gr.aueb.mscis.productCrawlerServer.crawler.similarity.SimilarityCalculator;
import gr.aueb.mscis.productCrawlerServer.db.model.SourceRecord;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductRequest;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductResponse;

public class ProductRequestHandler {
	private final static Logger logger = Logger.getLogger(ProductRequestHandler.class.getName());
	private final ProductFetcher productFetcher;
	private final IDocumentDownloader documentDownloader;
	SchemaMatcher schemaMatcher = new SchemaMatcher();
	SimilarityCalculator sc = new SimilarityCalculator();
	
	public ProductRequestHandler(IDocumentDownloader documentDownloader, ForkJoinPool forkJoinPool){
		this.documentDownloader = documentDownloader; 
		this.productFetcher = new ProductFetcher(forkJoinPool);
	}
	public List<ProductResponse> getProductsFilteredAndNested(List<SourceRecord> sources, ProductRequest productRequest){
		logger.info("Incoming product request: " + productRequest);
		logger.info("Downloading pages");
		List<Product> products = getProductsFromAllSourcesFlat(sources);
		logger.info("Found products: " + products.size());
		//schema matching
		logger.info("Schema matching and filtering");
		Stream<ProductSchema> productSchemaStream = products
				.stream()
				.map(pr->schemaMatcher.convert(pr))
				.filter(ProductSchema::isValid)
				.filter(ps -> ps.matchProductRequestCriteria(sc, productRequest, 0.8d));//basically is doing search with the name attribute
		
		//entity resolution
		logger.info("Entity Resolution");
		List<ProductSchema> filteredProducts = productSchemaStream.collect(Collectors.toList());
		logger.info("Valid product: " + filteredProducts.size());
		List<Set<ProductSchema>> lists =
			filteredProducts
			.stream()
			.map(ps -> filteredProducts.stream().filter(ps0 -> sc.isProductMatch(ps, ps0, 0.85d)).collect(Collectors.toSet()))
			.filter(set->!set.isEmpty())
			.collect(Collectors.toList());//the set contains all matched 

		List<Set<ProductSchema>> merged = new ArrayList<>();
		while(!lists.isEmpty()){
			Set<ProductSchema> current = lists.get(0);
			Set<ProductSchema> localMerge = lists.stream().filter(set-> set.stream().filter(it->current.contains(it)).findAny().isPresent()).reduce((set0, set1)-> {set0.addAll(set1); return set0;}).get();
			Iterator<Set<ProductSchema>> it = lists.iterator();
			while(it.hasNext()){
				Set<ProductSchema> possibleRemove = it.next();
				if (possibleRemove.stream().filter(ps->localMerge.contains(ps)).findAny().isPresent())
					it.remove();
			}	
			merged.add(localMerge);
		}
		
		return 
				merged
				.stream()
				.map(set-> new ProductResponse(set))
				.collect(Collectors.toList());
	}
	public List<Product> getProductsFromAllSourcesFlat(List<SourceRecord> sources) {
		DocumentSelectorCreator urlToSelector = new DocumentSelectorCreator(sources);
		Stream<DocumentMeta> documents = productFetcher.downloadDocuments(documentDownloader, urlToSelector.getAllUrlsWithEncodingFromSource().stream());
		Stream<UrlWithContentEncoding> urls = productFetcher.extractProductPagesFromMainCategoryDocument(urlToSelector.paginationPagesFromDocuments(documents));
		documents = productFetcher.downloadDocuments(documentDownloader, urls);
		urls = productFetcher.extractProductUrlsFromProductPageDocuments(urlToSelector.productUrlPagesFromDocuments(documents));
		documents = productFetcher.downloadDocuments(documentDownloader, urls);
		return productFetcher.extractProductFromProductDocument(urlToSelector.productsFromDocuments(documents)).collect(Collectors.toList());
	}
}
