package gr.aueb.mscis.productCrawlerServer.matcher;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;


public class MatcherRegexTest {
	Pattern pattern;
	
	/*private void matcherPrinter(Matcher matcher){
		System.out.println(matcher.toString());
		while(matcher.find()){
			System.out.println("group: " + matcher.group());
			for(int i=1; i<=matcher.groupCount(); i++){
				System.out.println("group("+i+") is " + matcher.group(i));
			}
		}
	}*/
	
	
	public final String manufacturerValueRegex = ".*";
	public final String screenResolutionValueRegex = "(\\d+)\\s{0,1}(x|X)\\s{0,1}(\\d+)";
	public final String ramValueRegex = "(\\d+)(\\.){0,1}(\\d*)";
//	public final String screenSizeValueRegex = "(\\d+)(\\.){0,1}(\\d*)(\\s){0,1}(('){1,2}|(\"))";
	public final String screenSizeValueRegex = "(\\d+)(\\.){0,1}(\\d*)(\\s){0,1}(('){1,2}|(\"){0,1}|(inches){0,1})";

	public final String storageValueRegex = "(\\d+)(\\.){0,1}(\\d*)";
	public final String cameraResolutionValueRegex = "(\\d+)(\\.){0,1}(\\d*)";
	public final String operatingSystemValueRegex = "(?i)(android|ios|apple|windows|blackberry)";
	public final String batteryValueRegex = "(?i)(\\d+)(\\s){0,1}(mAh)";
	public final String networkValueRegex = "(?i)(GSM|GPRS|EDGE|HSDPA|HSUPA|HSPA|LTE|2g|3g|4g\\+|4g)";
	public final String weightValueRegex = "(?i)(\\d+)(\\s){0,1}(gr|g){0,1}";
	
	
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
	@Test
	public void screenResolutionValue(){
		pattern = Pattern.compile(screenResolutionValueRegex);
		
		String value0 = "2960 x 1440 pixels";
		String value1 = "3'' TFT, 240 x 320";
		String value2 = "320X240 @ 15 FPS";
		
		Assert.assertEquals("2960 x 1440", getFirstMatch(pattern.matcher(value0)).orElse(""));
		Assert.assertEquals("240 x 320", getFirstMatch(pattern.matcher(value1)).orElse(""));
		Assert.assertEquals("320X240", getFirstMatch(pattern.matcher(value2)).orElse(""));
		
		
	}
	
	
	@Test
	public void ramValue(){
		pattern = Pattern.compile(ramValueRegex);
		
		String value0 = "32 GB";
		String value1 = "0.25 GB";
		String value2 = "1 GB";
		
		Assert.assertEquals("32", getFirstMatch(pattern.matcher(value0)).orElse(""));
		Assert.assertEquals("0.25", getFirstMatch(pattern.matcher(value1)).orElse(""));
		Assert.assertEquals("1", getFirstMatch(pattern.matcher(value2)).orElse(""));
		
	}
	
	@Test
	public void screenSizeValue(){
		pattern = Pattern.compile(screenSizeValueRegex);
		
		String value0 = "4'', TFT capacitive touchscreen, 480x800";
		String value1 = "5'', IPS LCD, 480x854";
		String value2 = "2.8'', TFT capacitive touchscreen, 240x320";
		String value3 = "4.5\"";
		String value4 = "4\"";
		String value5 = "6.2\"";
		String value6 = "4.7 inches";
		
		Assert.assertEquals("4''", getFirstMatch(pattern.matcher(value0)).orElse(""));
		Assert.assertEquals("5''", getFirstMatch(pattern.matcher(value1)).orElse(""));
		Assert.assertEquals("2.8''", getFirstMatch(pattern.matcher(value2)).orElse(""));
		Assert.assertEquals("4.5\"", getFirstMatch(pattern.matcher(value3)).orElse(""));
		Assert.assertEquals("4\"", getFirstMatch(pattern.matcher(value4)).orElse(""));
		Assert.assertEquals("6.2\"", getFirstMatch(pattern.matcher(value5)).orElse(""));
		Assert.assertEquals("4.7 ", getFirstMatch(pattern.matcher(value6)).orElse(""));
	}
	
	
	@Test
	public void storageValue(){
		pattern = Pattern.compile(storageValueRegex);
		
		String value0 = "1.5 GB";
		String value1 = "4 GB";
		
		Assert.assertEquals("1.5", getFirstMatch(pattern.matcher(value0)).orElse(""));
		Assert.assertEquals("4", getFirstMatch(pattern.matcher(value1)).orElse(""));	
	}
	
	
	@Test
	public void cameraResolutionValue(){
		pattern = Pattern.compile(cameraResolutionValueRegex);
		
		String value0 = "14";
		String value1 = "Οπίσθια: 13MP /f2.2/phase detect autofocus/dual-LED (dual tone) flash. Εμπρόσθια: 5MP";
		String value2 = "Οπίσθια: 12.3MP /f2.0/phase detect & laser autofocus/dual-LED (dual tone) flash. Εμπρόσθια: 8MP";
		String value3 = "16 MP";
		String value4 = "7 MP";
		String value5 = "12 + 12 MP";
		
		Assert.assertEquals("14", getFirstMatch(pattern.matcher(value0)).orElse(""));
		Assert.assertEquals("13", getFirstMatch(pattern.matcher(value1)).orElse(""));
		Assert.assertEquals("12.3", getFirstMatch(pattern.matcher(value2)).orElse(""));
		Assert.assertEquals("16", getFirstMatch(pattern.matcher(value3)).orElse(""));
		Assert.assertEquals("7", getFirstMatch(pattern.matcher(value4)).orElse(""));
		Assert.assertEquals("12", getFirstMatch(pattern.matcher(value5)).orElse(""));	
	}
	
	
	@Test
	public void operatingSystemValue(){
		pattern = Pattern.compile(operatingSystemValueRegex);
		
		String value0 = "APPLE IOS 9";
		String value1 = "APPLE IOS 8";
		String value2 = "BLACKBERRY 10";
		String value3 = "WINDOWS PHONE 8.1";
		String value4 = "ANDROID V6.0 MARSHMALLOW";
		String value5 = "ANDROID V7.0 NOUGAT";
		String value6 = "Android 6.0 Marshmallow";
		String value7 = "iOS 10";
		String value8 = "Android 7.0 Nougat";
		
		Assert.assertEquals("APPLE", getFirstMatch(pattern.matcher(value0)).orElse(""));
		Assert.assertEquals("APPLE", getFirstMatch(pattern.matcher(value1)).orElse(""));
		Assert.assertEquals("BLACKBERRY", getFirstMatch(pattern.matcher(value2)).orElse(""));
		Assert.assertEquals("WINDOWS", getFirstMatch(pattern.matcher(value3)).orElse(""));
		Assert.assertEquals("ANDROID", getFirstMatch(pattern.matcher(value4)).orElse(""));
		Assert.assertEquals("ANDROID", getFirstMatch(pattern.matcher(value5)).orElse(""));
		Assert.assertEquals("Android", getFirstMatch(pattern.matcher(value6)).orElse(""));
		Assert.assertEquals("iOS", getFirstMatch(pattern.matcher(value7)).orElse(""));
		Assert.assertEquals("Android", getFirstMatch(pattern.matcher(value8)).orElse(""));
	}
	
	@Test
	public void batteryValue(){
		pattern = Pattern.compile(batteryValueRegex);
		
		String value0 = "3000 mAh";
		String value1 = "2600 mAh";
		String value2 = "2770mAh Li-Ion Μη αποσπώμενη";
		String value3 = "1642mAh Li-Po Μη αποσπώμενη";
		String value4 = "1400mAh Li-Ion Αποσπώμενη";
		
		Assert.assertEquals("3000 mAh", getFirstMatch(pattern.matcher(value0)).orElse(""));
		Assert.assertEquals("2600 mAh", getFirstMatch(pattern.matcher(value1)).orElse(""));
		Assert.assertEquals("2770mAh", getFirstMatch(pattern.matcher(value2)).orElse(""));
		Assert.assertEquals("1642mAh", getFirstMatch(pattern.matcher(value3)).orElse(""));
		Assert.assertEquals("1400mAh", getFirstMatch(pattern.matcher(value4)).orElse(""));

	}
	
	@Test
	public void networkValue(){
		pattern = Pattern.compile(networkValueRegex);
		
		String value0 = "GSM, HSPA";
		String value1 = "GSM, HSPA, LTE";
		String value2 = "GPRS, EDGE, HSDPA: 7.2 Mbps, HSUPA: 5.76 Mbps";
		String value3 = "GSM, HSPA, LTE";
		String value4 = "GSM / CDMA / HSPA / LTE";
		String value5 = "3G, 4G";
		String value6 = "4G";
		String value7 = "4G+";
		String value8 = "3G";
		String value9 = "2G";
		
		Assert.assertEquals("HSPA", getLastMatch(pattern.matcher(value0)).orElse(""));
		Assert.assertEquals("LTE", getLastMatch(pattern.matcher(value1)).orElse(""));
		Assert.assertEquals("HSUPA", getLastMatch(pattern.matcher(value2)).orElse(""));
		Assert.assertEquals("LTE", getLastMatch(pattern.matcher(value3)).orElse(""));
		Assert.assertEquals("LTE", getLastMatch(pattern.matcher(value4)).orElse(""));
		Assert.assertEquals("4G", getLastMatch(pattern.matcher(value5)).orElse(""));
		Assert.assertEquals("4G", getLastMatch(pattern.matcher(value6)).orElse(""));
		Assert.assertEquals("4G+", getLastMatch(pattern.matcher(value7)).orElse(""));
		Assert.assertEquals("3G", getLastMatch(pattern.matcher(value8)).orElse(""));
		Assert.assertEquals("2G", getLastMatch(pattern.matcher(value9)).orElse(""));

	}
	
	@Test
	public void weightValue(){
		pattern = Pattern.compile(weightValueRegex);
		
		String value0 = "112";
		String value1 = "100.5";
		String value2 = "157 gr";
		String value3 = "188 gr";
		
		Assert.assertEquals("112", getFirstMatch(pattern.matcher(value0)).orElse(""));
		Assert.assertEquals("100", getFirstMatch(pattern.matcher(value1)).orElse(""));
		Assert.assertEquals("157 gr", getFirstMatch(pattern.matcher(value2)).orElse(""));
		Assert.assertEquals("188 gr", getFirstMatch(pattern.matcher(value3)).orElse(""));

	}
	
}
