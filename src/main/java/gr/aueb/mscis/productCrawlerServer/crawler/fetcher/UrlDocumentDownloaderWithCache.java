package gr.aueb.mscis.productCrawlerServer.crawler.fetcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotDownloadDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;
import gr.aueb.mscis.productCrawlerServer.utils.FileUtils;

public class UrlDocumentDownloaderWithCache implements IDocumentDownloader{
	private static final Logger logger = Logger.getLogger(UrlDocumentDownloaderWithCache.class.getName());
	private static final String hashAlgorith = "MD5";
	@Override
	public Document downloadDocument(String urlStr, String charset)
			throws CannotDownloadDocumentException, CannotParseDocumentException {
		
		Optional<String> md5HashOfUrlOpt = createMD5ofUrl(urlStr);
		File file = new File("documentCache/"+md5HashOfUrlOpt.orElse("_"));
		if (file.exists()){
			return Jsoup.parse(FileUtils.fileToString(file.getPath()).orElseThrow( 
					()->   new CannotParseDocumentException("Cannot parse document saved in cache with name " + file.getPath())   ));
		}

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
			Document result = Jsoup.parse(httpResultSB.toString(), StandardCharsets.UTF_8.toString());
			if (md5HashOfUrlOpt.isPresent()){
				FileUtils.writeToFile("documentCache/"+md5HashOfUrlOpt.get(), result.toString());
			}
			return result;
		} catch (Exception e) {
			throw new CannotParseDocumentException("Cannot parse document from url " + urlStr, e);
		}
	}
	private StringBuilder readFromStream(URLConnection connection, String charset){
		StringBuilder resultSB = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset))){
			String line = "";
			while((line=br.readLine()) != null){
				resultSB.append(line);
				resultSB.append("\n");
			}
			
		} catch (IOException e){
			logger.warn("Exception while handling input stream from url", e);
		}
		return resultSB;
	}
	private Optional<String> createMD5ofUrl(String url){
		try {
			MessageDigest md = MessageDigest.getInstance(hashAlgorith);
			byte[] messageDigest = md.digest(url.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			return Optional.ofNullable(number.toString(16));
		} catch (NoSuchAlgorithmException e) {
			logger.warn("Cannot hash url because algorithm " + hashAlgorith + " does not exist in this system", e);
			return Optional.empty();
		}
		
	}
	
}
