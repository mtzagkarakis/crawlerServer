package gr.aueb.mscis.productCrawlerServer.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class FileUtils {
	public static Optional<String> fileToString(String filename){
		StringBuilder contentBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filename));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
			return Optional.empty();
		}
		return Optional.ofNullable(contentBuilder.toString());
	}
	
	public static void writeToFile(String filename, String content){
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
				if (bw!=null)
					bw.close();
				if (fw!=null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
