package gr.aueb.mscis.productCrawlerServer.crawler.similarity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductSchema;
import info.debatty.java.stringsimilarity.Cosine;
import info.debatty.java.stringsimilarity.JaroWinkler;

public class SimilarityCalculator {

	/**
	 * If find a match for all tokens in search string then true
	 * @param actual
	 * @param search
	 * @return
	 */
	public boolean isSearchMatch(String actual, String search, double threshold){
		List<String> actualTokens = Arrays.asList(actual.split("\\s")).stream().distinct().filter(str->str.length()>0).collect(Collectors.toList());
		List<String> searchTokens = Arrays.asList(search.split("\\s")).stream().distinct().filter(str->str.length()>0).collect(Collectors.toList());
		
		return 
				searchTokens
					.parallelStream()
					.filter(stoken-> actualTokens.parallelStream().filter(at-> equalsJaroWinkler(stoken, at, threshold)).count() > 0)
					.count() == searchTokens.size()?true:false;
	}
	
	public boolean equalsJaroWinkler(String str0, String str1, double threshold){
		JaroWinkler jw = new JaroWinkler();
		double similarity = jw.similarity(str0, str1);
		return similarity>=threshold?true:false;
	}
	
	public boolean equalsCosine4(String str0, String str1, double threshold){
		Cosine cos = new Cosine(4);
		double similarity = cos.similarity(str0, str1);
		boolean result = similarity>=threshold?true:false;
		if (result && !str0.equalsIgnoreCase(str1))
			System.out.println("TRUE from " + str0 + " AND " + str1);
		return result;
	}
	
	public boolean isProductMatch(ProductSchema ps0, ProductSchema ps1, double threshold){
		boolean sameName = false, sameScreenSize = false, sameRam = false, sameManufacturer = false, sameResolution=false;
		
		if (equalsJaroWinkler(ps0.getNameCleared(), ps1.getNameCleared(), threshold))
			sameName = true;
		
		if (ps0.getManufacturer().equalsIgnoreCase(ps1.getManufacturer()))
			sameManufacturer = true;
		
		if (ps0.getScreenResolution().equalsIgnoreCase(ps1.getScreenResolution()))
			sameResolution = true;
		
		if (ps0.getScreenSize() == ps1.getScreenSize())
			sameScreenSize = true;
		
		if (ps0.getRam().intValue() == ps1.getRam().intValue())
			sameRam = true;
		
		
		
		return sameName&&sameManufacturer&&sameRam&&(sameScreenSize||sameResolution);
	}
}
