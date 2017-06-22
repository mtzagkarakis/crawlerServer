package gr.aueb.mscis.productCrawlerServer.crawler.fetcher;

import java.util.ArrayList;
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
	private final IDocumentDownloader docDownloader;
	public ProductFetcher(ForkJoinPool forkJoinPool, IDocumentDownloader docDownloader) {
		this.customThreadPool = forkJoinPool;
		this.docDownloader = docDownloader;
	}
	
	
	public Stream<DocumentMeta> downloadDocuments(Stream<UrlWithContentEncoding> productUrls){
		try{
			return customThreadPool.submit(() -> 
						productUrls
						.parallel()
						.map(this::downloadDoc)
						.filter(Objects::nonNull)
					).get();
		} catch (InterruptedException | ExecutionException e) {
			logger.warn("Exception in thread pool" + e.getMessage(), e);
			return Stream.empty();
		}
	}
	private DocumentMeta downloadDoc(UrlWithContentEncoding pUrl){
		try{
			logger.info("Downloading document with url: " + pUrl.getUrlAsString());
			return new DocumentMeta(docDownloader.downloadDocument(pUrl.getUrlAsString(), pUrl.getEncoding()), pUrl.getUrl(), pUrl.getEncoding());
		} catch (CannotDownloadDocumentException | CannotParseDocumentException e) {
			logger.warn("Failed to fetch product page with url " + pUrl.getUrl().toString() + " with message " + e.getMessage(), e);
			return null;
		}
	}
	public Stream<UrlWithContentEncoding> extractProductPagesFromMainCategoryDocument(Stream<DocumentMetaWithSelector<PaginationSelector>> documents){
		return documents
				.map(doc-> {
					try {
						List<ProductPageUrl> productPageList = parser.extractCategoryPageUrlsFromCategoryPageDocument(doc.getDocument(), doc.getUrl(), doc.getData() );
						return 
							productPageList.stream().map(ppUrl->new UrlWithContentEncoding(ppUrl.getUrl(), doc.getEncoding())).collect(Collectors.toList());
					} catch (CannotParseDocumentException e) {
						logger.warn("Failed to extract pagination pages from main page " + e.getMessage(), e);
						return null;
					}
				})
				.filter(Objects::nonNull)
				.flatMap(List::stream);
	}

	public Stream<UrlWithContentEncoding> extractProductUrlsFromProductPageDocuments(Stream<DocumentMetaWithSelector<ProductURLFromPaginationSelector>> categoryPageDocumentsWithItemSelector){
		return categoryPageDocumentsWithItemSelector
				.map(doc -> {
					List<UrlWithContentEncoding> urlsWithEncoding = new ArrayList<>(); 
					try {						
						return parser.extractProductUrlFromCategoryPageDocument(doc.getDocument(), doc.getUrl(), doc.getData())
								.stream()
								.map(pUrl->new UrlWithContentEncoding(pUrl.getUrl(), doc.getEncoding()))
								.collect(Collectors.toList());
					} catch (CannotParseDocumentException e) {
						logger.warn("Failed to extract some productUrls", e);
					}
					return urlsWithEncoding;
				})
				.flatMap(List::stream);
	}

	public Stream<Product> extractProductFromProductDocument(Stream<DocumentMetaWithSelector<ProductSelector>> productDocuments){
			return productDocuments
						.map(pDoc->{
							try {
								return parser.extractProductFromDocument(pDoc.getDocument(), pDoc.getUrl(), pDoc.getData());
							} catch (CannotParseDocumentException e) {
								logger.warn("Cannot Extract product info from document with url " + pDoc.getUrl() + " reason: " + e.getMessage(), e);
								return null;
							}
						})
						.filter(Objects::nonNull);
	}
}
