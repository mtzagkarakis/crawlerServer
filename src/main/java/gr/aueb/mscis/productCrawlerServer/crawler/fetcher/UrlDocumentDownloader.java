package gr.aueb.mscis.productCrawlerServer.crawler.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotDownloadDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;

public class UrlDocumentDownloader implements IDocumentDownloader{
	private final Logger logger = Logger.getLogger(UrlDocumentDownloader.class.getName());
	@Override
	public Document downloadDocument(String urlStr, String charset)
			throws CannotDownloadDocumentException, CannotParseDocumentException {
		StringBuilder httpResultSB = null;
		try {
			URL url = new URL(urlStr);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(2*60_000);
			connection.setReadTimeout(2*60_000);
			connection.setRequestProperty("Referer", "www.google.com");
			connection.setRequestProperty("User-Agent", UserAgentFetcher.getRandomUserAgent());
			httpResultSB = readFromStream(connection, charset);
		} catch (IOException e) {
			throw new CannotDownloadDocumentException(e.getMessage(), e);
		}
		try{
			return Jsoup.parse(httpResultSB.toString(), StandardCharsets.UTF_8.toString());
		} catch (Exception e) {
			throw new CannotParseDocumentException("Cannot parse document from url " + urlStr, e);
		}
	}
	private StringBuilder readFromStream(URLConnection connection, String charset){
		StringBuilder resultSB = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset))){
			char[] buffer = new char[1024];
			while(br.read(buffer) != -1){
				resultSB.append(buffer);
			}
		} catch (IOException e){
			logger.warn("Exception while handling input stream from url", e);
		}
		return resultSB;
	}
}
