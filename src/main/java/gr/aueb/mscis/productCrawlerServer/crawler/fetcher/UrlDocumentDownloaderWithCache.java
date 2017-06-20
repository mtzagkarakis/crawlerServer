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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotDownloadDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;
import gr.aueb.mscis.productCrawlerServer.utils.FileUtils;

public class UrlDocumentDownloaderWithCache implements IDocumentDownloader{
	
	@Override
	public Document downloadDocument(String urlStr, String charset)
			throws CannotDownloadDocumentException, CannotParseDocumentException {
		
		Optional<String> md5HashOfUrlOpt = createMD5ofUrl(urlStr);
		File file = new File("documentCache/"+md5HashOfUrlOpt.orElse("_"));
		if (file.exists()){
			return Jsoup.parse(FileUtils.fileToString(file.getPath()).orElseThrow( 
					()->   new CannotParseDocumentException("Cannot parse document saved in cache with name " + file.getPath())   ));
		}
		
		
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
			Document result = Jsoup.parse(resultSB.toString(), StandardCharsets.UTF_8.toString());
			if (md5HashOfUrlOpt.isPresent()){
				FileUtils.writeToFile("documentCache/"+md5HashOfUrlOpt.get(), result.toString());
			}
			return result;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new CannotParseDocumentException("Cannot parse document from url " + urlStr, e);
		}
	}
	
	private Optional<String> createMD5ofUrl(String url){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(url.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			return Optional.ofNullable(number.toString(16));
		} catch (NoSuchAlgorithmException e) {
			return Optional.empty();
		}
		
	}
	
}
