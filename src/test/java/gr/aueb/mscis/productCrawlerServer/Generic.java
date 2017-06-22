package gr.aueb.mscis.productCrawlerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.jsoup.nodes.Document;
import org.junit.Test;

import gr.aueb.mscis.productCrawlerServer.crawler.fetcher.UrlDocumentDownloader;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotDownloadDocumentException;
import gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions.CannotParseDocumentException;
import gr.aueb.mscis.productCrawlerServer.utils.FileUtils;

public class Generic {
	@Test
	public void p() throws CannotDownloadDocumentException, CannotParseDocumentException, IOException{
		UrlDocumentDownloader downloader = new UrlDocumentDownloader();
		Document doc = downloader.downloadDocument("http://www.citisshop.com/tilefona/kinita-tilefona/ola-ta-smartphones", "utf-8");
		FileUtils.writeToFile("temp.html", doc.toString());
		/*URL url = new URL("http://www.citisshop.com/tilefona/kinita-tilefona/ola-ta-smartphones");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		print_content(con);*/
	}
	private void print_content(HttpURLConnection con){
		if(con!=null){

		try {

		   System.out.println("****** Content of the URL ********");
		   BufferedReader br =
			new BufferedReader(
				new InputStreamReader(con.getInputStream()));

		   String input;

		   while ((input = br.readLine()) != null){
		      System.out.println(input);
		   }
		   br.close();

		} catch (IOException e) {
		   e.printStackTrace();
		}

	       }

	   }

}
