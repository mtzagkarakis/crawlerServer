package gr.aueb.mscis.productCrawlerServer.httpController;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import gr.aueb.mscis.productCrawlerServer.crawler.model.DocumentMeta;
import gr.aueb.mscis.productCrawlerServer.crawler.model.DocumentMetaWithSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.UrlWithContentEncoding;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.PaginationSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductURLFromPaginationSelector;
import gr.aueb.mscis.productCrawlerServer.db.model.SourceRecord;

public class DocumentSelectorCreator {
	private final static Logger logger = Logger.getLogger(DocumentSelectorCreator.class.getName());
	private final static String warnMessage = "Cannot match url to any eshop: ";
	private final List<SourceRecord> sources;
	public DocumentSelectorCreator(List<SourceRecord> sources){
		this.sources = sources;
	}
	public List<UrlWithContentEncoding> getAllUrlsWithEncodingFromSource(){
		return	sources.stream().map(source -> {
				try {
					return new UrlWithContentEncoding(source.getCategoryUrl(), source.getEncoding());
				} catch (MalformedURLException e) {
					logger.warn("Ivalid categoryUrl in DB", e);
					return null;
				}
			})
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
	}
	/*public List<DocumentMetaWithSelector<PaginationSelector>> paginationPagesFromDocuments(List<DocumentMeta> documents){
		return documents.stream().map(this::paginationPageForDocument).filter(Objects::nonNull).collect(Collectors.toList());
	} */
	public Stream<DocumentMetaWithSelector<PaginationSelector>> paginationPagesFromDocuments(Stream<DocumentMeta> documents){
		return documents.map(this::paginationPageForDocument).filter(Objects::nonNull);//.collect(Collectors.toList());
	} 
	private DocumentMetaWithSelector<PaginationSelector> paginationPageForDocument(DocumentMeta document){
		Optional<SourceRecord> sourceOpt = sources.stream().filter(source-> document.getUrl().contains(source.getName())).findAny();
		if (sourceOpt.isPresent())
			return new DocumentMetaWithSelector<>(document, sourceOpt.get().getPaginationSelector());
		else{
			logger.warn(warnMessage + document.getUrl());
			return null;
		}
	}
	/*public List<DocumentMetaWithSelector<ProductURLFromPaginationSelector>> productUrlPagesFromDocuments(List<DocumentMeta> documents){
		return documents.stream().map(this::productUrlPageFromDocuments).filter(Objects::nonNull).collect(Collectors.toList());
	}*/
	public Stream<DocumentMetaWithSelector<ProductURLFromPaginationSelector>> productUrlPagesFromDocuments(Stream<DocumentMeta> documents){
		return documents.map(this::productUrlPageFromDocuments).filter(Objects::nonNull);
	}
	private DocumentMetaWithSelector<ProductURLFromPaginationSelector> productUrlPageFromDocuments(DocumentMeta document){
		Optional<SourceRecord> sourceOpt = sources.stream().filter(source-> document.getUrl().contains(source.getName())).findAny();
		if (sourceOpt.isPresent())
			return new DocumentMetaWithSelector<>(document, sourceOpt.get().getProductURLFromPaginationSelector());
		else{
			logger.warn(warnMessage + document.getUrl());
			return null;
		}
	}
	/*public List<DocumentMetaWithSelector<ProductSelector>> productsFromDocuments(List<DocumentMeta> documents){
		return documents.stream().map(this::productFromDocuments).filter(Objects::nonNull).collect(Collectors.toList());
	}*/
	public Stream<DocumentMetaWithSelector<ProductSelector>> productsFromDocuments(Stream<DocumentMeta> documents){
		return documents.map(this::productFromDocuments).filter(Objects::nonNull);
	}
	private DocumentMetaWithSelector<ProductSelector> productFromDocuments(DocumentMeta document){
		Optional<SourceRecord> sourceOpt = sources.stream().filter(source-> document.getUrl().contains(source.getName())).findAny();
		if (sourceOpt.isPresent())
			return new DocumentMetaWithSelector<>(document, sourceOpt.get().getProductSelector());
		else {
			logger.warn(warnMessage + document.getUrl());
			return null;
		}
	}
	
}
