package gr.aueb.mscis.productCrawlerServer.crawler.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotDownloadDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;

public class UrlDocumentDownloader implements IDocumentDownloader{
	
	@Override
	public Document downloadDocument(String urlStr, String charset)
			throws CannotDownloadDocumentException, CannotParseDocumentException {
		BufferedReader br = null;
		StringBuilder resultSB = new StringBuilder();
		try {
			URL url = new URL(urlStr);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(2*60_000);
			connection.setReadTimeout(2*60_000);
			connection.setRequestProperty("Referer", "www.google.com");
			connection.setRequestProperty("User-Agent", UserAgentFetcher.getRandomUserAgent());
			br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line = "";
			while((line = br.readLine())!= null){
				resultSB.append(line);
			}
		} catch (IOException e) {
			throw new CannotDownloadDocumentException(e.getMessage(), e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try{
			return Jsoup.parse(resultSB.toString(), StandardCharsets.UTF_8.toString());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new CannotParseDocumentException("Cannot parse document from url " + urlStr, e);
		}
	}

}
