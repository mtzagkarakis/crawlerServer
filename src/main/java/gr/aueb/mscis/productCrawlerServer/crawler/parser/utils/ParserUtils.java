package gr.aueb.mscis.productCrawlerServer.crawler.parser.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;

public class ParserUtils {
	private static final Logger logger = Logger.getLogger(ParserUtils.class.getName());
	
	public static Optional<Integer> getUrlNumberParameter(String url, String parameterName){
		Optional<Integer> lastPageNumber = Optional.empty();
		try {
			String lastPageNumberString =
				URLEncodedUtils.parse(new URI(url), StandardCharsets.UTF_8)
				.stream()
				.filter(nameValuePair->nameValuePair.getName().equalsIgnoreCase(parameterName))
				.map(nameValuePair-> nameValuePair.getValue())
				.findFirst().orElse("");
			
			try{
				lastPageNumber = Optional.ofNullable(Integer.valueOf(lastPageNumberString));
			} catch (NumberFormatException e) {
				logger.error("Page parameter is not a number");
				return Optional.empty();
			}
		} catch (URISyntaxException e) {
			logger.error("Last Page is not a valid Url");
			return Optional.empty();
		}
		return lastPageNumber;
	}
	
	public static Optional<String> getUrlParameter(String url, String parameterName){
		Optional<String> parameterValue = Optional.empty();
		try {
			parameterValue = 
				URLEncodedUtils.parse(new URI(url), StandardCharsets.UTF_8)
				.stream()
				.filter(nameValuePair->nameValuePair.getName().equalsIgnoreCase(parameterName))
				.map(nameValuePair-> nameValuePair.getValue())
				.findFirst();
		} catch (URISyntaxException e) {
			logger.error("Not a valid Url: " + url);
			return Optional.empty();
		}
		return parameterValue;
	}
	
}
