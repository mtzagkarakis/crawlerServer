package gr.aueb.mscis.productCrawlerServer.httpController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import gr.aueb.mscis.productCrawlerServer.crawler.fetcher.UrlDocumentDownloader;
import gr.aueb.mscis.productCrawlerServer.crawler.fetcher.UrlDocumentDownloaderWithCache;
import gr.aueb.mscis.productCrawlerServer.db.EntityManagerUtils;
import gr.aueb.mscis.productCrawlerServer.db.model.SourceRecord;
import gr.aueb.mscis.productCrawlerServer.httpController.model.GenericResponse;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductRequest;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductResponse;
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
	
	
	public Route getProductCached(){
		return (req, res)->{
			
			List<SourceRecord> sources = 
				EntityManagerUtils.doInTransaction(em-> {
					return em.createQuery("select sr from SourceRecord sr where sr.isActive>0", SourceRecord.class).getResultList();
				});
			if (sources != null){
				sources = sources.stream().filter(sr->sr.validate(logger)).collect(Collectors.toList());
			}
			ProductRequestHandler prh = getProductRequestHandlerCached();
			try{
				Map<String, String> query = req.queryMap().toMap().entrySet().stream().collect(Collectors.toMap(en->en.getKey(), en->en.getValue()[0]));
				return new GenericResponse<ProductResponse>(prh.getProductsFilteredAndNested(sources, new ProductRequest(query)), 200).serializeToJson();
			} catch (Exception e) {
				logger.error("Error While getting products", e);
				return new GenericResponse<>(Arrays.asList(), 500).serializeToJson();
			}
		};
	}
	public Route getProduct(){
		return (req, res)->{
			
			List<SourceRecord> sources = 
					EntityManagerUtils.doInTransaction(em-> {
						return em.createQuery("select sr from SourceRecord sr where sr.isActive>0", SourceRecord.class).getResultList();
					});
			if (sources != null){
				sources = sources.stream().filter(sr->sr.validate(logger)).collect(Collectors.toList());
			}
			ProductRequestHandler prh = getProductRequestHandler();
			try{
				Map<String, String> query = req.queryMap().toMap().entrySet().stream().collect(Collectors.toMap(en->en.getKey(), en->en.getValue()[0]));
				return new GenericResponse<ProductResponse>(prh.getProductsFilteredAndNested(sources, new ProductRequest(query)), 200).serializeToJson();
			} catch (Exception e) {
				logger.error("Error While getting products", e);
				return new GenericResponse<>(Arrays.asList(), 500).serializeToJson();
			}
		};
	}
}
