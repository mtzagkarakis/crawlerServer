package gr.aueb.mscis.productCrawlerServer.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Properties{
	private static final Logger logger = Logger.getLogger( Properties.class.getName() );
	java.util.Properties mProps;
	public Properties(String filename){
		mProps = new java.util.Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(filename);
		} catch (FileNotFoundException e){
			logger.log(Level.ERROR, "File not found", e);
		} catch (SecurityException e){
			logger.log(Level.ERROR, "Security exception", e);
		}
		if (in != null){
			try {
				mProps.load( in );
			} catch(IOException e){
				logger.log(Level.ERROR, "IO exception", e);	
			} catch (IllegalArgumentException e){
				logger.log(Level.ERROR, "Illegal Argunemnt", e);
			}
		}
		
	}
	private String getProperty(String key){
        String res = mProps.getProperty(key);
        if( res == null || res.compareTo("") == 0 ){
            return null;
        }
        return res;
    }
	@SuppressWarnings("unused")
	private boolean getBooleanProperty(String key){
		if (getProperty(key) == null)
			return false;
		if (getProperty(key).equalsIgnoreCase("true"))
			return true;
		else if (getProperty(key).equalsIgnoreCase("false"))
			return false;
		else 
			return false;
	}
	public Optional<Integer> getPort(){
		try{
			return Optional.of(Integer.parseInt(getProperty("server.port")));
		} catch (NullPointerException | NumberFormatException e) {
			return Optional.empty();
		}
	}
	public Optional<Integer> getParallelThreads(){
		try{
			return Optional.of(Integer.parseInt(getProperty("server.parallelThreads")));
		} catch (NullPointerException | NumberFormatException e){
			return Optional.empty();
		}
	}
	public Optional<String> getDBUrl(){
		return Optional.ofNullable(getProperty("server.db.url"));
	}
	public Optional<String> getDBUsername(){
		return Optional.ofNullable(getProperty("server.db.username"));
	}
	public Optional<String> getDBPassword(){
		return Optional.ofNullable(getProperty("server.db.password"));
	}
	
	
	/*public Optional<String> getManufacturerValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.manufacturerValueRegex"));
	}
	public Optional<String> getManufacturerKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.manufacturerKeyRegex"));
	}
	
	public Optional<String> getScreenResolutionValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.screenResolutionValueRegex"));
	}
	public Optional<String> getScreenResolutionKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.screenResolutionKeyRegex"));
	}
	
	public Optional<String> getRamValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.ramValueRegex"));
	}
	public Optional<String> getRamKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.ramKeyRegex"));
	}
	
	public Optional<String> getScreenSizeValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.screenSizeValueRegex"));
	}
	public Optional<String> getScreenSizeKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.screenSizeKeyRegex"));
	}
	
	public Optional<String> getStorageValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.storageValueRegex"));
	}
	public Optional<String> getStorageKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.storageKeyRegex"));
	}
	
	public Optional<String> getCameraResolutionValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.cameraResolutionValueRegex"));
	}
	public Optional<String> getCameraResolutionKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.cameraResolutionKeyRegex"));
	}
	
	public Optional<String> getOperatingSystemValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.operatingSystemValueRegex"));
	}
	public Optional<String> getOperatingSystemKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.operatingSystemKeyRegex"));
	}
	
	public Optional<String> getBatteryValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.batteryValueRegex"));
	}
	public Optional<String> getBatteryKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.batteryKeyRegex"));
	}
	
	public Optional<String> getNetworkValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.networkValueRegex"));
	}
	public Optional<String> getNetworkKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.networkKeyRegex"));
	}
	
	public Optional<String> getWeightValueRegex(){
		return Optional.ofNullable(getProperty("server.matcher.weightValueRegex"));
	}
	public Optional<String> getWeightKeyRegex(){
		return Optional.ofNullable(getProperty("server.matcher.weightKeyRegex"));
	}*/
}
