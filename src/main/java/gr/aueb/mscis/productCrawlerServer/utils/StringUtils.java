package gr.aueb.mscis.productCrawlerServer.utils;

public class StringUtils {
	public static boolean stringIsEmptyOrNull(String str){
		if (str != null && str.length()>0)
			return false;
		return true;
	}
}
