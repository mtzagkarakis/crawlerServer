package gr.aueb.mscis.productCrawlerServer.httpController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import gr.aueb.mscis.productCrawlerServer.crawler.fetcher.UrlDocumentDownloader;
import gr.aueb.mscis.productCrawlerServer.crawler.fetcher.UrlDocumentDownloaderWithCache;
import gr.aueb.mscis.productCrawlerServer.db.EntityManagerUtils;
import gr.aueb.mscis.productCrawlerServer.db.model.SourceRecord;
import gr.aueb.mscis.productCrawlerServer.httpController.model.GenericResponse;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductRequestQuery;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductResponse;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductResponseList;
import spark.Route;

public class ProductController {
	private final static Logger logger = Logger.getLogger(ProductController.class.getName());
	private final ForkJoinPool forkJoinPool;
	public ProductController(ForkJoinPool forkJoinPool){
		this.forkJoinPool = forkJoinPool;
	}
	
	private ProductRequestHandler getProductRequestHandlerCached(){
		return new ProductRequestHandler(new UrlDocumentDownloaderWithCache(), forkJoinPool);
	}
	private ProductRequestHandler getProductRequestHandler(){
		return new ProductRequestHandler(new UrlDocumentDownloader(), forkJoinPool);
	}
	
	private static final String downloadTimeAttributeName = "downloadTime";
	private static final String entityResolutionTimeAttributeName = "entityResolutionTime";
	private static final String totalTimeAttributeName = "totalTime";
	
	public Route getProductCached(){
		return (req, res)->{
			List<SourceRecord> sources = 
				EntityManagerUtils.doInTransactionAndReturn(em-> 
					em.createQuery("select sr from SourceRecord sr where sr.isActive>0", SourceRecord.class).getResultList()
				);
			if (sources != null){
				sources = sources.stream().filter(sr->sr.validate(logger)).collect(Collectors.toList());
			}
			ProductRequestHandler prh = getProductRequestHandlerCached();
			try{
				Map<String, String> query = req.queryMap().toMap().entrySet().stream().collect(Collectors.toMap(Entry::getKey, en->en.getValue()[0]));
				
				ProductResponseList productResponseList = prh.getProductsFilteredAndNested(sources, new ProductRequestQuery(query));
				
				return new GenericResponse<ProductResponse>(productResponseList.getMatchedProductsList(), 200, createResponseAttributeMap(productResponseList)).serializeToJson();
			} catch (Exception e) {
				logger.error("Error While getting products", e);
				res.status(500);
				return new GenericResponse<ProductResponse>(Arrays.asList(), 500, null).serializeToJson();
			}
		};
	}
	public Route getProduct(){
		return (req, res)->{
			List<SourceRecord> sources = 
					EntityManagerUtils.doInTransactionAndReturn(em-> 
						em.createQuery("select sr from SourceRecord sr where sr.isActive>0", SourceRecord.class).getResultList()
					);
			if (sources != null){
				sources = sources.stream().filter(sr->sr.validate(logger)).collect(Collectors.toList());
			}
			ProductRequestHandler prh = getProductRequestHandler();
			try{
				Map<String, String> query = req.queryMap().toMap().entrySet().stream().collect(Collectors.toMap(Entry::getKey, en->en.getValue()[0]));
				
				ProductResponseList productResponseList = prh.getProductsFilteredAndNested(sources, new ProductRequestQuery(query));
				
				return new GenericResponse<ProductResponse>(productResponseList.getMatchedProductsList(), 200, createResponseAttributeMap(productResponseList)).serializeToJson();
			} catch (Exception e) {
				logger.error("Error While getting products", e);
				res.status(500);
				return new GenericResponse<ProductResponse>(Arrays.asList(), 500, null).serializeToJson();
			}
		};
	}
	
	private Map<String, Object> createResponseAttributeMap(ProductResponseList productResponseList){
		Map<String, Object> attributes = new HashMap<>();
		attributes.put(downloadTimeAttributeName, productResponseList.getDownloadAndParseTime());
		attributes.put(entityResolutionTimeAttributeName, productResponseList.getEntityResolutionTime());
		attributes.put(totalTimeAttributeName, productResponseList.getDownloadAndParseTime() + productResponseList.getEntityResolutionTime());
		return attributes;
	}
}
