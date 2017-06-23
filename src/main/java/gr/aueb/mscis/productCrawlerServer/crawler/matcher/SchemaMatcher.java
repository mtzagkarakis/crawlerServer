package gr.aueb.mscis.productCrawlerServer.crawler.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import gr.aueb.mscis.productCrawlerServer.crawler.model.Product;
import gr.aueb.mscis.productCrawlerServer.crawler.model.ProductSchema;

public class SchemaMatcher {
	private final Logger logger = Logger.getLogger(SchemaMatcher.class.getName());
	
	private static final String notNumberRegex = "[^\\d.]";
	
	public static final String manufacturers = "acer,alcatel,allview,amazon,amoi,apple,archos,asus,at&t,benefon,benq,benq-siemens,bird,blackberry,blu,bosch,bq,casio,cat,celkon,chea,coolpad,dell,emporia,energizer,ericsson,eten,fujitsu siemens,garmin-asus,gigabyte,gionee,google,haier,hp,htc,huawei,i-mate,i-mobile,icemobile,innostream,inq,intex,jolla,karbonn,kyocera,lava,leeco,lenovo,lg,maxon,maxwest,meizu,micromax,microsoft,mitac,mitsubishi,modu,motorola,mwg,nec,neonode,niu,nokia,nvidia,o2,oneplus,oppo,orange,palm,panasonic,pantech,parla,philips,plum,posh,prestigio,qmobile,qtek,sagem,samsung,sendo,sewon,sharp,siemens,sonim,sony,sony ericsson,spice,t-mobile,tel.me.,telit,thuraya,toshiba,unnecto,vertu,verykool,vivo,vk mobile,vodafone,wiko,wnd,xcute,xiaomi,xolo,yezz,yota,yu,zte";
	private static final Set<String> manufacturersSet = Arrays.asList(manufacturers.split(",")).stream().collect(Collectors.toSet());
	public static final String manufacturerValueRegex = ".*";
	public static final String manufacturerKeyRegex = "(μάρκα|μαρκα|κατασκευαστής|κατασκευαστης)";
	private static Pattern manufacturerValuePattern = Pattern.compile(manufacturerValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern manufacturerKeyPattern = Pattern.compile(manufacturerKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	public static final String screenResolutionValueRegex = "(\\d+)\\s{0,1}(x|X)\\s{0,1}(\\d+)";
	public static final String screenResolutionKeyRegex = "(Οθόνη|οθονη|ανάλυση|αναλυση)";
	private static Pattern screenResolutionValuePattern = Pattern.compile(screenResolutionValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern screenResolutionKeyPattern = Pattern.compile(screenResolutionKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	public static final String ramValueRegex = "(\\d+)(\\.){0,1}(\\d*)(\\s){0,1}((GB)|(MB)|(GB RAM)){0,1}";
	public static final String ramKeyRegex = "(RAM|μνήμη|μνημη)";
	private static Pattern ramValuePattern = Pattern.compile(ramValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern ramKeyPattern = Pattern.compile(ramKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	public static final String screenSizeValueRegex = "(\\d+)(\\.){0,1}(\\d*)(\\s){0,1}(('){1,2}|(\"){1}|(”){1}|(inches){1})";
	public static final String screenSizeKeyRegex = "(μέγεθος οθόνης|μεγεθος οθονης|οθόνη|οθονη)";
	private static Pattern screenSizeValuePattern = Pattern.compile(screenSizeValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern screenSizeKeyPattern = Pattern.compile(screenSizeKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	public static final String storageValueRegex = "(\\d+)(\\.){0,1}(\\d){0,2}(\\s){0,1}((GB)|(MB))";
	public static final String storageKeyRegex = "(Αποθηκευτικός Χώρος|Αποθ. Χώρος|Εσωτερική μνήμη|Αποθηκευτικος Χωρος|Εσωτερικη μνημη|χωρητικότητα)";
	private static Pattern storageValuePattern = Pattern.compile(storageValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern storageKeyPattern = Pattern.compile(storageKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	public static final String cameraResolutionValueRegex = "(\\d+)(\\.){0,1}(\\d*)";
	public static final String cameraResolutionKeyRegex = "(Πίσω|πισω|Camera|κάμερα|καμερα)";
	private static Pattern cameraResolutionValuePattern = Pattern.compile(cameraResolutionValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern cameraResolutionKeyPattern = Pattern.compile(cameraResolutionKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	public static final String operatingSystemValueRegex = "(android|ios|apple|windows|blackberry)";
	public static final String operatingSystemKeyRegex = "(Λειτουργικό|λειτουργικο|Λειτ. Σύστημα)";
	private static Pattern operatingSystemValuePattern = Pattern.compile(operatingSystemValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern operatingSystemKeyPattern = Pattern.compile(operatingSystemKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	public static final String batteryValueRegex = "(\\d+)(\\s){0,1}(mAh)";
	public static final String batteryKeyRegex = "(Μπαταρία|μπαταρια)";
	private static Pattern batteryValuePattern = Pattern.compile(batteryValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern batteryKeyPattern = Pattern.compile(batteryKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	public static final String networkValueRegex = "(GSM|GPRS|EDGE|HSDPA|HSUPA|HSPA|LTE|2g|3g|4g\\+|4g)";
	public static final String networkKeyRegex = "(Δίκτυ|δικτυ)|(Mobile Internet)";
	private static Pattern networkValuePattern = Pattern.compile(networkValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern networkKeyPattern = Pattern.compile(networkKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	public static final String weightValueRegex = "(\\d+)(\\s){0,1}(gr|g){0,1}";
	public static final String weightKeyRegex = "(Βάρος|βαρος)";
	private static Pattern weightValuePattern = Pattern.compile(weightValueRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	private static Pattern weightKeyPattern = Pattern.compile(weightKeyRegex, Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);
	
	
	private final List<String> validations = new ArrayList<>();
	public ProductSchema convert(Product product){
		validations.clear();
		
		ProductSchema.Builder builder = new ProductSchema.Builder();
		builder.setName(findName(product))
		.setPrice(findPrice(product))
		.setManufacturer(findManufacturer(product))
		.setScreenResolution(findScreenResolution(product))
		.setRam(findRam(product))
		.setScreenSize(findScreenSize(product))
		.setStorageInGB(findStorage(product))
		.setCameraResolutionInMP(findCameraResolution(product))
		.setOperatingSystem(findOperatingSystem(product))
		.setBatteryInMamp(findBattery(product))
		.setNetwork(findNetwork(product))
		.setWeight(findWeight(product));
		
		ProductSchema ps =new ProductSchema(builder, product);
		logger.info("Schema matched product: " + ps);
		return ps;
	}
	
	private String findName(Product product){
		return product.getName().toLowerCase().replaceAll("(i?)(κινητό|κινητο|mobile|smartphone)", "").trim();
	}
	private Double findPrice(Product product){
		try{
			return Double.valueOf(product.getPrice());
		} catch (NumberFormatException e) {
			return -1.0d;
		}
	}
	private String findManufacturer(Product product){
		if (product.getManufacturer().length() > 0){
			return product.getManufacturer().toLowerCase();
		}
		List<String> possibleValues = findPossibeAttributeExactMatch(product, manufacturerKeyPattern, manufacturerValuePattern);
		if (!possibleValues.isEmpty()){
			return possibleValues.get(0).trim().toLowerCase();
		}
		
		Optional<String> manFromNameOpt = Arrays.asList(product.getName().toLowerCase().split("\\s")).stream().filter(manufacturersSet::contains).findFirst();
		if (manFromNameOpt.isPresent())
			return manFromNameOpt.get().toLowerCase();
		
		validations.add("Did not find Manufacturer");
		return "";
	}
	private class ComparatorOfIntegerStrings implements Comparator<String>{
		public int compare(String str0, String str1){
			try{
				return Integer.valueOf(str0).compareTo(Integer.valueOf(str1));
			} catch (NumberFormatException e) {
				logger.debug("Compairing string as number failed for pair " + str0 + " and " + str1, e);
				return 0;
			}
		}
	}
	private String findScreenResolution(Product product){
		List<String> possibleValues = findPossibleAttributesFirstMatch(product, screenResolutionKeyPattern, screenResolutionValuePattern);
		
		if (possibleValues.isEmpty()){
			validations.add("Did not find Screen Resolution");
			return "";
		}
		if (possibleValues.size() > 1){
			validations.add("Found Multiple Screen Resolution");
		}
		String result = possibleValues.get(0).replace(" ", "");
		result = Arrays.asList(result.split("x")).stream().sorted(new ComparatorOfIntegerStrings()).collect(Collectors.joining("x"));
		return result;
	}
	
	private Double findRam(Product product){
		List<String> possibleValues = findPossibleAttributesLastMatch(product, ramKeyPattern, ramValuePattern);
			
		if (possibleValues.isEmpty()){
			validations.add("Did not find RAM");
			return -1.0d;
		}
		if (possibleValues.size() > 1){
			validations.add("Found Multiple RAM");
		}
		return possibleValues
				.stream()
				.map(val -> val.replaceAll(notNumberRegex, "").trim())
				.map(Double::parseDouble)
				.map(ram -> ram>257.0d?ram/1024d:ram)
				.sorted()
				.findFirst()
				.orElse(-1.0d);
	}
	
	private Double findScreenSize(Product product){
		List<String> possibleValues = findPossibleAttributesFirstMatch(product, screenSizeKeyPattern, screenSizeValuePattern);
		
		possibleValues = possibleValues.stream().map(val->val.replace("'", "").replace("\"", "").trim()).collect(Collectors.toList());
		if (possibleValues.isEmpty()){
			validations.add("Did not find Screen Size");
			return -1.0d;
		}
		if (possibleValues.size() > 1){
			validations.add("Found Multiple Screen Size");
		}
		try{
			return Double.valueOf(possibleValues.get(0).replaceAll(notNumberRegex, "").trim());
		} catch (NumberFormatException e) {
			validations.add("Parse Screen Size failed: " + possibleValues.get(0));
			return -1.0d;
		}
	}
	
	private Double findStorage(Product product){
		List<String> possibleValues = findPossibleAttributesFirstMatch(product, storageKeyPattern, storageValuePattern);
			
		if (possibleValues.isEmpty()){
			validations.add("Did not find Storage");
			return -1.0d;
		}
		if (possibleValues.size() > 1){
			validations.add("Found Multiple Storage");
		}
		try{
			Double storage = Double.valueOf(possibleValues.get(0).replaceAll(notNumberRegex, "").trim());
			if (storage > 1000){
				storage = storage/1000.d;
			}
			return storage;
		} catch (NumberFormatException e) {
			validations.add("Parse Storage failed: " + possibleValues.get(0));
			return -1.0d;
		}
	}
	
	private Double findCameraResolution(Product product){
		List<String> possibleValues = findPossibleAttributesFirstMatch(product, cameraResolutionKeyPattern, cameraResolutionValuePattern);
			
		if (possibleValues.isEmpty()){
			validations.add("Did not find Camera Resolution");
			return -1.0d;
		}
		if (possibleValues.size() > 1){
			validations.add("Found Multiple Camera Resolution");
		}
		try{
			return Double.valueOf(possibleValues.get(0).replaceAll(notNumberRegex, "").trim());
		} catch (NumberFormatException e) {
			validations.add("Parse Camera Resolution failed: " + possibleValues.get(0));
			return -1.0d;
		}
	}
	private final static String appleKeyword = "apple";
	private final static String iosKeyword = "ios";
	private final static String iphoneKeyword = "iphone";
	private final static String androidKeyword = "android";
	private String findOperatingSystem(Product product){
		List<String> possibleValues = findPossibleAttributesFirstMatch(product, operatingSystemKeyPattern, operatingSystemValuePattern);
		
		if (!possibleValues.isEmpty()){
			return possibleValues.get(0).trim().toLowerCase().replaceAll(appleKeyword, iosKeyword);
		
		}
		
		Optional<String> osOpt = Arrays.asList(product.getName().split("\\s")).stream()
				.filter(tok -> appleKeyword.equalsIgnoreCase(tok) || iosKeyword.equalsIgnoreCase(tok) || iphoneKeyword.equalsIgnoreCase(tok) || androidKeyword.equalsIgnoreCase(tok))
				.findFirst();
		if (osOpt.isPresent()){
			return osOpt.get().toLowerCase().replaceAll("(apple|iphone)", "ios");
		}
		
		
		validations.add("Did not find Operating System");
		return "";
		
	}
	
	private Integer findBattery(Product product){
		List<String> possibleValues = findPossibleAttributesFirstMatch(product, batteryKeyPattern, batteryValuePattern);
			
		if (possibleValues.isEmpty()){
			validations.add("Did not find Battery");
			return -1;
		}
		if (possibleValues.size() > 1){
			validations.add("Found Multiple Battery");
		}
		try{
			return Integer.valueOf(possibleValues.get(0).replaceAll(notNumberRegex, "").trim());
		} catch (NumberFormatException e) {
			validations.add("Parse Battery failed: " + possibleValues.get(0));
			return -1;
		}
	}
	
	private String findNetwork(Product product){
		List<String> possibleValues = findPossibleAttributesLastMatch(product, networkKeyPattern, networkValuePattern);
		
		if (possibleValues.isEmpty()){
			validations.add("Did not find Network");
			return "";
		}
		if (possibleValues.size() > 1){
			validations.add("Found Multiple Network");
		}
		return possibleValues.get(0).replaceAll("(GSM|GPRS|EDGE)", "2G").replaceAll("(HSDPA|HSUPA|HSPA)", "3G").replace("LTE", "4G").replace(" ", "");
	}
	
	private Integer findWeight(Product product){
		List<String> possibleValues = findPossibleAttributesFirstMatch(product, weightKeyPattern, weightValuePattern);
			
		if (possibleValues.isEmpty()){
			validations.add("Did not find Weight");
			return -1;
		}
		if (possibleValues.size() > 1){
			validations.add("Found Multiple Weight");
		}
		try{
			return Integer.valueOf(possibleValues.get(0).replaceAll(notNumberRegex, "").trim());
		} catch (NumberFormatException e) {
			validations.add("Parse Weight failed: " + possibleValues.get(0));
			return -1;
		}
	}
	private List<String> findPossibeAttributeExactMatch(Product product, Pattern keyPattern, Pattern valuePattern){
		return product.getAttributes().keySet()
				.stream()
				.filter(key-> keyPattern.matcher(key).matches())
				.map(key -> product.getAttributes().get(key))
				.map(value -> getFirstMatch(valuePattern.matcher(value)))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}
	private List<String> findPossibleAttributesFirstMatch(Product product, Pattern keyPatter, Pattern valuePattern){
		return 
			product.getAttributes().keySet()
				.stream()
				.filter(key-> keyPatter.matcher(key).find())
				.map(key -> product.getAttributes().get(key))
				.map(value -> getFirstMatch(valuePattern.matcher(value)))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}
	private List<String> findPossibleAttributesLastMatch(Product product, Pattern keyPatter, Pattern valuePattern){
		return 
			product.getAttributes().keySet()
				.stream()
				.filter(key-> keyPatter.matcher(key).find())
				.map(key -> product.getAttributes().get(key))
				.map(value -> getLastMatch(valuePattern.matcher(value)))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}
	private Optional<String> getFirstMatch(Matcher matcher){
		if (matcher.find()){
			return Optional.of(matcher.group());
		}
		return Optional.empty();
	}
	private Optional<String> getLastMatch(Matcher matcher){
		Optional<String> last = Optional.empty();
		while (matcher.find()){
			last = Optional.of(matcher.group());
		}
		return last;
	}
	
}
