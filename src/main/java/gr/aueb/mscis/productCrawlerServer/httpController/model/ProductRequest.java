package gr.aueb.mscis.productCrawlerServer.httpController.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class ProductRequest {
	private final String searchString, screenResolution, manufacturer, network;
	private final Integer screesizeFrom, screensizeTo, ramFrom, ramTo, storageFrom, storageTo, 
				cameraFrom, cameraTo, weightFrom, weightTo, batteryFrom, batteryTo;
	private final boolean isAndroid, isIOS, isWindows, isOther;
	private final BigDecimal priceFrom, priceTo;
	/*?searchstring=123
	 * &screensize-from=4&
	 * screensize-to=6&
	 * ram-from=2&
	 * ram-to=4&
	 * screenresolution=480x640&
	 * storage-from=4&
	 * storage-to=16&
	 * camera-from=8
	 * &camera-to=12
	 * &android-os=1&
	 * apple-os=1
	 * &windows-os=1&
	 * other-os=1&
	 * weight-from=150&
	 * weight-to=210&
	 * battery-from=2500&
	 * battery-to=3000&
	 * company=apple
	 * &year=2012& --> not mapped
	 * price-from=50
	 * &price-to=500
	 * &network=3g
	τα os μόνο στην ουσία αν δεν είναι τσεκαρισμένα δε θα υπάρχουν πάνω καν στο query string*/
	public ProductRequest(Map<String, String> params){
		screesizeFrom = parseInt(params.get("screensize-from"));
		screensizeTo = parseInt(params.get("screensize-to"));
		ramFrom = parseInt(params.get("ram-from"));
		ramTo = parseInt(params.get("ram-to"));
		storageFrom = parseInt(params.get("storage-from"));
		storageTo = parseInt(params.get("storage-to"));
		cameraFrom = parseInt(params.get("camera-from"));
		cameraTo = parseInt(params.get("camera-to"));
		isAndroid = parseIntAsBoolean(params.get("android-os"));
		isIOS = parseIntAsBoolean(params.get("apple-os"));
		isWindows = parseIntAsBoolean(params.get("windows-os"));
		isOther = parseIntAsBoolean(params.get("other-os"));
		weightFrom = parseInt(params.get("weight-from"));
		weightTo = parseInt(params.get("weight-to"));
		batteryFrom = parseInt(params.get("battery-from"));
		batteryTo = parseInt(params.get("battery-to"));
		priceFrom = parseBigDecimal(params.get("price-from"));
		priceTo = parseBigDecimal(params.get("price-to"));
		
		searchString = parseString(params.get("searchstring"));
		screenResolution = parseString(params.get("screenresolution"));
		manufacturer = parseString(params.get("company"));
		network = parseString(params.get("network"));
	}
	private String parseString(String str){
		if (str == null) return null;
		return str.isEmpty()?null:str;
	}
	private Integer parseInt(String str){
		if (str == null) return null;
		try{
			return Integer.valueOf(str);
		} catch (NullPointerException | NumberFormatException e) {
			return null;
		}
	}
	
	private BigDecimal parseBigDecimal(String str){
		if (str == null) return null;
		try{
			return (new BigDecimal(str)).setScale(2, RoundingMode.HALF_UP);
		} catch (NullPointerException | NumberFormatException e) {
			return null;
		}
	}
	
	private boolean parseIntAsBoolean(String str){
		if (str == null) return false;
		try{
			int val = Integer.valueOf(str);
			if (val == 1)
				return true;
			return false;
		} catch (NullPointerException | NumberFormatException e) {
			return false;
		}
	}
	public String getSearchString() {
		return searchString;
	}
	public String getScreenResolution() {
		return screenResolution;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public String getNetwork() {
		return network;
	}
	public Integer getScreesizeFrom() {
		return screesizeFrom;
	}
	public Integer getScreensizeTo() {
		return screensizeTo;
	}
	public Integer getRamFrom() {
		return ramFrom;
	}
	public Integer getRamTo() {
		return ramTo;
	}
	public Integer getStorageFrom() {
		return storageFrom;
	}
	public Integer getStorageTo() {
		return storageTo;
	}
	public Integer getCameraFrom() {
		return cameraFrom;
	}
	public Integer getCameraTo() {
		return cameraTo;
	}
	public Integer getWeightFrom() {
		return weightFrom;
	}
	public Integer getWeightTo() {
		return weightTo;
	}
	public Integer getBatteryFrom() {
		return batteryFrom;
	}
	public Integer getBatteryTo() {
		return batteryTo;
	}
	public boolean isAndroid() {
		return isAndroid;
	}
	public boolean isIOS() {
		return isIOS;
	}
	public boolean isWindows() {
		return isWindows;
	}
	public boolean isOther() {
		return isOther;
	}
	public BigDecimal getPriceFrom() {
		return priceFrom;
	}
	public BigDecimal getPriceTo() {
		return priceTo;
	}
	@Override
	public String toString() {
		return "ProductRequest [searchString=" + searchString + ", screenResolution=" + screenResolution
				+ ", manufacturer=" + manufacturer + ", network=" + network + ", screesizeFrom=" + screesizeFrom
				+ ", screensizeTo=" + screensizeTo + ", ramFrom=" + ramFrom + ", ramTo=" + ramTo + ", storageFrom="
				+ storageFrom + ", storageTo=" + storageTo + ", cameraFrom=" + cameraFrom + ", cameraTo=" + cameraTo
				+ ", weightFrom=" + weightFrom + ", weightTo=" + weightTo + ", batteryFrom=" + batteryFrom
				+ ", batteryTo=" + batteryTo + ", isAndroid=" + isAndroid + ", isIOS=" + isIOS + ", isWindows="
				+ isWindows + ", isOther=" + isOther + ", priceFrom=" + priceFrom + ", priceTo=" + priceTo + "]";
	}
	
}