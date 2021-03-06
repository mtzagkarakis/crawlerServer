package gr.aueb.mscis.productCrawlerServer.httpController;

import java.time.Duration;
import java.time.Instant;
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
import gr.aueb.mscis.productCrawlerServer.db.model.SourceRecord;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductRequestQuery;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductResponse;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductResponseList;

public class ProductRequestHandler {
	private final static Logger logger = Logger.getLogger(ProductRequestHandler.class.getName());
	private final ProductFetcher productFetcher;
	SchemaMatcher schemaMatcher = new SchemaMatcher();
	
	public ProductRequestHandler(IDocumentDownloader documentDownloader, ForkJoinPool forkJoinPool){ 
		this.productFetcher = new ProductFetcher(forkJoinPool, documentDownloader);
	}
	public ProductResponseList getProductsFilteredAndNested(List<SourceRecord> sources, ProductRequestQuery productRequest){
		logger.info("Incoming product request: " + productRequest);
		logger.info("Downloading pages");
		
		//schema matching
		logger.info("Schema matching");
		Instant downloadStart = Instant.now();
		List<ProductSchema> filteredProducts = getProductsFromAllSourcesFlat(sources).map(pr->schemaMatcher.convert(pr)).collect(Collectors.toList());
		Instant downloadFinish = Instant.now();
		logger.info("Found products: " + filteredProducts.size());
		
		//entity resolution
		logger.info("Entity Resolution");
		Instant entityResolutionStart = Instant.now();
		List<Set<ProductSchema>> lists =
			filteredProducts
			.stream()
			.map(ps -> filteredProducts.stream().filter(ps0 -> ps.isProductMatch(ps0, 0.90d)).collect(Collectors.toSet()))
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
		
		List<ProductResponse> 
			responseSets = 
				merged
				.stream()
				.filter(set -> set.stream().filter(prSchema->prSchema.isValid(productRequest) && prSchema.matchProductRequestCriteria(productRequest, 0.80d)).findAny().isPresent())
				.map(set-> new ProductResponse(set))
				.collect(Collectors.toList());
		logger.info("Result set size: " + responseSets.size() + " avarage set size: " + responseSets.stream().map(pr->pr.getProductSet().size()).collect(Collectors.averagingInt(size->size)).doubleValue());
		Instant entityResolutionFinish = Instant.now();
		return new ProductResponseList(responseSets, Duration.between(downloadStart, downloadFinish).toMillis(), Duration.between(entityResolutionStart, entityResolutionFinish).toMillis());
		
	}
	public Stream<Product> getProductsFromAllSourcesFlat(List<SourceRecord> sources) {
		DocumentSelectorCreator urlToSelector = new DocumentSelectorCreator(sources);
		Stream<DocumentMeta> documents = productFetcher.downloadDocuments(urlToSelector.getAllUrlsWithEncodingFromSource().stream());
		Stream<UrlWithContentEncoding> urls = productFetcher.extractProductPagesFromMainCategoryDocument(urlToSelector.paginationPagesFromDocuments(documents));
		documents = productFetcher.downloadDocuments(urls);
		urls = productFetcher.extractProductUrlsFromProductPageDocuments(urlToSelector.productUrlPagesFromDocuments(documents));
		documents = productFetcher.downloadDocuments(urls);
		return productFetcher.extractProductFromProductDocument(urlToSelector.productsFromDocuments(documents));//.collect(Collectors.toList());
	}
}
