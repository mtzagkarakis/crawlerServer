package gr.aueb.mscis.productCrawlerServer.crawler.fetcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import gr.aueb.mscis.productCrawlerServer.crawler.model.DocumentMeta;
import gr.aueb.mscis.productCrawlerServer.crawler.model.DocumentMetaWithSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.Product;
import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductPageUrl;
import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductUrl;
import gr.aueb.mscis.productCrawlerServer.crawler.model.UrlWithContentEncoding;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.PaginationSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductURLFromPaginationSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.GenericParser;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotDownloadDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;

public class ProductFetcher {
	private static final Logger logger = Logger.getLogger(ProductFetcher.class.getName());
	private final GenericParser parser = new GenericParser();
	private final ForkJoinPool customThreadPool;
	public ProductFetcher(ForkJoinPool forkJoinPool) {
		this.customThreadPool = forkJoinPool;
	}
	
	
	public Stream<DocumentMeta> downloadDocuments(IDocumentDownloader docDownloader, Stream<UrlWithContentEncoding> productUrls){
		try{
			return customThreadPool.submit(() -> 
						productUrls
						.parallel()
						.map(pUrl-> {
							try{
								logger.debug("Downloading document with url: " + pUrl.getUrlAsString());
								return new DocumentMeta(docDownloader.downloadDocument(pUrl.getUrlAsString(), pUrl.getEncoding()), pUrl.getUrlAsString(), pUrl.getEncoding());
							} catch (CannotDownloadDocumentException | CannotParseDocumentException e) {
								logger.warn("Failed to fetch product page with url " + pUrl.getUrl().toString() + " with message " + e.getMessage());
								return null;
							}
						})
						.filter(Objects::nonNull)
					).get();
		} catch (InterruptedException | ExecutionException e) {
			logger.warn("Thread exception" + e.getMessage()!=null?e.getMessage():"");
			return Stream.empty();//new ArrayList<>();
		}
	}
	
	
	/*public Stream<DocumentMeta> downloadDocuments(IDocumentDownloader docDownloader, List<UrlWithContentEncoding> productUrls){
		try{
			Collections.shuffle(productUrls);
			return customThreadPool.submit(() -> 
						streamDocuments(docDownloader, productUrls)
						//.collect(Collectors.toList())
					).get();
		} catch (InterruptedException | ExecutionException e) {
			logger.warn("Thread exception" + e.getMessage()!=null?e.getMessage():"");
			return Stream.empty();//new ArrayList<>();
		}
	}
	
	public Stream<DocumentMeta> streamDocuments(IDocumentDownloader docDownloader, List<UrlWithContentEncoding> productUrls){
		return 
			productUrls
			.parallelStream()
			.map(pUrl-> {
				try{
					logger.debug("Downloading document with url: " + pUrl.getUrlAsString());
					return new DocumentMeta(docDownloader.downloadDocument(pUrl.getUrlAsString(), pUrl.getEncoding()), pUrl.getUrlAsString(), pUrl.getEncoding());
				} catch (CannotDownloadDocumentException | CannotParseDocumentException e) {
					logger.warn("Failed to fetch product page with url " + pUrl.getUrl().toString() + " with message " + e.getMessage());
					return null;
				}
			})
			.filter(Objects::nonNull);
	}*/
	
	
	/*Map<String, List<UrlWithContentEncoding>> productUrlsGroupedByHost =  
	productUrls
	.stream()
	.collect(Collectors.groupingBy(UrlWithContentEncoding::getUrlHost, Collectors.toList()))

productUrlsGroupedByHost
	.entrySet()
	.parallelStream()
	.map(pUrl-> {
		try{
			logger.debug("Downloading document with url: " + pUrl.getUrlAsString());
			return new DocumentMeta(docDownloader.downloadDocument(pUrl.getUrlAsString(), pUrl.getEncoding()), pUrl.getUrlAsString(), pUrl.getEncoding());
		} catch (CannotDownloadDocumentException | CannotParseDocumentException e) {
			logger.warn("Failed to fetch product page with url " + pUrl.getUrl().toString() + " with message " + e.getMessage());
			return null;
		}
	})
	.filter(Objects::nonNull);*/
	/*public List<UrlWithContentEncoding> extractProductPagesFromMainCategoryDocument(List<DocumentMetaWithSelector<PaginationSelector>> documents){
		return documents.stream()
				.map(doc-> {
					try {
						List<ProductPageUrl> productPageList = parser.extractCategoryPageUrlsFromCategoryPageDocument(doc.getDocument(), doc.getData() );
						return 
							productPageList.stream().map(ppUrl->new UrlWithContentEncoding(ppUrl.getUrl(), doc.getEncoding())).collect(Collectors.toList());
					} catch (CannotParseDocumentException e) {
						logger.warn("Failed to extract pagination pages from main page " + e.getMessage());
						return null;
					}
				})
				.filter(list->list != null)
				.flatMap(pUrlList->pUrlList.stream())
				.collect(Collectors.toList());
	}*/
	public Stream<UrlWithContentEncoding> extractProductPagesFromMainCategoryDocument(Stream<DocumentMetaWithSelector<PaginationSelector>> documents){
		return documents
				.map(doc-> {
					try {
						List<ProductPageUrl> productPageList = parser.extractCategoryPageUrlsFromCategoryPageDocument(doc.getDocument(), doc.getData() );
						return 
							productPageList.stream().map(ppUrl->new UrlWithContentEncoding(ppUrl.getUrl(), doc.getEncoding())).collect(Collectors.toList());
					} catch (CannotParseDocumentException e) {
						logger.warn("Failed to extract pagination pages from main page " + e.getMessage());
						return null;
					}
				})
				.filter(list->list != null)
				.flatMap(pUrlList->pUrlList.stream());
	}
	
	
	
	/*public List<UrlWithContentEncoding> extractProductUrlsFromProductPageDocuments(List<DocumentMetaWithSelector<ProductURLFromPaginationSelector>> categoryPageDocumentsWithItemSelector){
		return categoryPageDocumentsWithItemSelector
				.stream()
				.map(doc -> {
					try {
						List<ProductUrl> productPageUrlsList = parser.extractProductUrlFromCategoryPageDocument(doc.getDocument(), doc.getData());
						return 
							productPageUrlsList.stream().map(pUrl->new UrlWithContentEncoding(pUrl.getUrl(), doc.getEncoding())).collect(Collectors.toList());
					} catch (CannotParseDocumentException e) {
						logger.warn("Failed to extract some productUrls");
						return new ArrayList<UrlWithContentEncoding>();
					}
				})
				.flatMap(ppList->ppList.stream())
				.collect(Collectors.toList());
	}*/
	public Stream<UrlWithContentEncoding> extractProductUrlsFromProductPageDocuments(Stream<DocumentMetaWithSelector<ProductURLFromPaginationSelector>> categoryPageDocumentsWithItemSelector){
		return categoryPageDocumentsWithItemSelector
				.map(doc -> {
					try {
						List<ProductUrl> productPageUrlsList = parser.extractProductUrlFromCategoryPageDocument(doc.getDocument(), doc.getData());
						return 
							productPageUrlsList.stream().map(pUrl->new UrlWithContentEncoding(pUrl.getUrl(), doc.getEncoding())).collect(Collectors.toList());
					} catch (CannotParseDocumentException e) {
						logger.warn("Failed to extract some productUrls");
						return new ArrayList<UrlWithContentEncoding>();
					}
				})
				.flatMap(ppList->ppList.stream());
	}
	
	/*public List<Product> extractProductFromProductDocument(List<DocumentMetaWithSelector<ProductSelector>> productDocuments){
		return productDocuments
					.stream()
					.map(pDoc->{
						try {
							return parser.extractProductFromDocument(pDoc.getDocument(), pDoc.getUrl(), pDoc.getData());
						} catch (CannotParseDocumentException e) {
							return null;
						}
					})
					.filter(product->product != null)
					.collect(Collectors.toList());
	}*/
	public Stream<Product> extractProductFromProductDocument(Stream<DocumentMetaWithSelector<ProductSelector>> productDocuments){
			return productDocuments
						.map(pDoc->{
							try {
								return parser.extractProductFromDocument(pDoc.getDocument(), pDoc.getUrl(), pDoc.getData());
							} catch (CannotParseDocumentException e) {
								return null;
							}
						})
						.filter(product->product != null);
	}
}
