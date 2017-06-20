package gr.aueb.mscis.productCrawlerServer.crawler.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserAgentFetcher {
	private static List<String> userAgentStrings = new ArrayList<>();
	static {
		InputStream is = null;
		BufferedReader br = null;
		try{
			is = UserAgentFetcher.class.getResourceAsStream("/files/user-agents.txt");
			br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8.toString()));
			String line = "";
			while((line=br.readLine())!= null){
				userAgentStrings.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static String getRandomUserAgent(){
		if (userAgentStrings.size() == 0)
			return "";
		if (userAgentStrings.size() == 1)
			return userAgentStrings.get(0);
		
		Random rand = new Random();
		return userAgentStrings.get(rand.nextInt(userAgentStrings.size()-1));
	}
}
