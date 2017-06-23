package gr.aueb.mscis.productCrawlerServer.crawler.model;

import java.util.HashMap;
import java.util.Map;

import gr.aueb.mscis.productCrawlerServer.crawler.similarity.SimilarityCalculator;
import gr.aueb.mscis.productCrawlerServer.httpController.model.ProductRequest;

public class ProductSchema{
	public static class Builder{
		private String name = "";
		private Double screenSize = -1.0d;
		private Double ram = -1.0d;
		private String screenResolution = "";
		private Double storageInGB = -1.0d;
		private Double cameraResolutionInMP = -1.0d;
		private String operatingSystem = "";
		private Integer batteryInMamp = -1;
		private String manufacturer = "";
		private Double price = -1.0d;
		private String network = "";
		private Integer weight = -1;
		private Map<String, String> attributes = new HashMap<>();
		public String getName() {
			return name;
		}
		public Double getScreenSize() {
			return screenSize;
		}
		public Double getRam() {
			return ram;
		}
		public String getScreenResolution() {
			return screenResolution;
		}
		public Double getStorageInGB() {
			return storageInGB;
		}
		public Double getCameraResolutionInMP() {
			return cameraResolutionInMP;
		}
		public String getOperatingSystem() {
			return operatingSystem;
		}
		public Integer getBatteryInMamp() {
			return batteryInMamp;
		}
		public String getManufacturer() {
			return manufacturer;
		}
		public Double getPrice() {
			return price;
		}
		public String getNetwork() {
			return network;
		}
		public Integer getWeight(){
			return weight;
		}
		public Map<String, String> getAttributes(){
			return attributes;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setScreenSize(Double screenSize) {
			this.screenSize = screenSize;
			return this;
		}
		public Builder setRam(Double ram) {
			this.ram = ram;
			return this;
		}
		public Builder setScreenResolution(String screenResolution) {
			this.screenResolution = screenResolution;
			return this;
		}
		public Builder setStorageInGB(Double storageInGB) {
			this.storageInGB = storageInGB;
			return this;
		}
		public Builder setCameraResolutionInMP(Double cameraResolutionInMP) {
			this.cameraResolutionInMP = cameraResolutionInMP;
			return this;
		}
		public Builder setOperatingSystem(String operatingSystem) {
			this.operatingSystem = operatingSystem;
			return this;
		}
		public Builder setBatteryInMamp(Integer batteryInMamp) {
			this.batteryInMamp = batteryInMamp;
			return this;
		}
		public Builder setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}
		public Builder setPrice(Double price) {
			this.price = price;
			return this;
		}
		public Builder setNetwork(String network) {
			this.network = network;
			return this;
		}
		public Builder setWeight(Integer weight){
			this.weight = weight;
			return this;
		}	 
	}
	
	private final String name;
	private final Double price;
	private final Double screenSize;
	private final Double ram;
	private final String screenResolution;
	private final Double storageInGB;
	private final Double cameraResolutionInMP;
	private final String operatingSystem;
	private final Integer batteryInMamp;
	private final String manufacturer;
	private final String network;
	private final Integer weight;
	private final Product product;
	public ProductSchema(Builder builder, Product product) {
		this.product = product;
		this.name = builder.getName();
		
		this.screenResolution = builder.getScreenResolution();
		this.manufacturer = builder.getManufacturer();
		this.network = builder.getNetwork();
		
		this.price = builder.getPrice();
		this.screenSize = builder.getScreenSize();
		this.ram = builder.getRam();
		this.storageInGB = builder.getStorageInGB();
		this.cameraResolutionInMP = builder.getCameraResolutionInMP();
		this.batteryInMamp = builder.getBatteryInMamp();
		this.weight = builder.getWeight();
		
		this.operatingSystem = builder.getOperatingSystem();
		
	}
	/*private boolean isValid(){
		System.out.println(this);
		if (name.length() == 0 
				||
				manufacturer.length() == 0
				||
				screenResolution.length() == 0
				||
				network.length() == 0
				||
				screenSize < 0
				||
				ram < 0
				||
				storageInGB < 0
				||
				cameraResolutionInMP < 0
				||
				batteryInMamp < 0
				||
				weight < 0
				||
				operatingSystem.length() == 0){
			return false;
		}
		return true;
	}*/
	public boolean matchProductRequestCriteria(SimilarityCalculator ssc, ProductRequest pr, double threshold){
		//ProductRequest [searchString=samsung, screenResolution=, 
		//manufacturer=, network=, screesizeFrom=4, screensizeTo=6, ramFrom=2, ramTo=4, 
		//storageFrom=4, storageTo=16, cameraFrom=8, cameraTo=12, weightFrom=150, weightTo=210, 
		//batteryFrom=2500, batteryTo=3000, isAndroid=false, isIOS=false, isWindows=false, isOther=false, priceFrom=null, priceTo=null]
		System.out.println(this.toString());
		if (name.length() > 0 && pr.getSearchString() != null && !ssc.isSearchMatch(name, pr.getSearchString().toLowerCase(), threshold))
			return false;
		
		if (screenResolution.length() > 0 && pr.getScreenResolution() != null && !pr.getScreenResolution().equalsIgnoreCase(this.screenResolution))
			return false;
		if (manufacturer.length() > 0 && pr.getManufacturer() != null && !pr.getManufacturer().equalsIgnoreCase(this.manufacturer))
			return false;
		if (network.length() > 0 && pr.getNetwork() != null && !pr.getNetwork().equalsIgnoreCase(this.network))
			return false;
		
		if(pr.getPriceFrom() != null && pr.getPriceFrom().doubleValue() > this.price)
			return false;
		if(pr.getPriceTo() != null && pr.getPriceTo().doubleValue() < this.price)
			return false;
		
		if(pr.getScreesizeFrom() != null && pr.getScreesizeFrom() > this.screenSize)
			return false;
		if(pr.getScreensizeTo() != null && pr.getScreensizeTo() < this.screenSize)
			return false;
		
		if(pr.getRamFrom() != null && pr.getRamFrom() > this.ram)
			return false;
		if(pr.getRamTo() != null && pr.getRamTo() < this.ram)
			return false;
		
		if(pr.getStorageFrom() != null && pr.getStorageFrom() > this.storageInGB)
			return false;
		if(pr.getStorageTo() != null && pr.getStorageTo() < this.storageInGB)
			return false;
		
		if(pr.getCameraFrom() != null && pr.getCameraFrom() > this.cameraResolutionInMP)
			return false;
		if(pr.getCameraTo() != null && pr.getCameraTo() < this.cameraResolutionInMP)
			return false;
		
		if(pr.getBatteryFrom() != null && pr.getBatteryFrom() > this.batteryInMamp)
			return false;
		if(pr.getBatteryTo() != null && pr.getBatteryTo() < this.batteryInMamp)
			return false;
				
		if(pr.getWeightFrom() != null && pr.getWeightFrom() > this.weight)
			return false;
		if(pr.getWeightTo() != null && pr.getWeightTo() < this.weight)
			return false;
		
		if (pr.isAndroid() && this.operatingSystem.equalsIgnoreCase("android"))
			return true;
		if (pr.isIOS() && this.operatingSystem.equalsIgnoreCase("ios"))
			return true;
		if (pr.isWindows() && this.operatingSystem.equalsIgnoreCase("windows"))
			return true;
		if (pr.isOther() && this.operatingSystem.length() > 0)
			return true;

		return true;
	}
	public Product getProduct(){
		return this.product;
	}
	public String getName() {
		return name;
	}
	public String getNameCleared(){
		return name.replaceAll("(?i)("+manufacturer+"|"+screenResolution+"|"+operatingSystem+"|"+network+"|"+storageInGB+")", "")
				.replaceAll("(?i)(GB|MB|gr|eu)\\+", "")
				.replaceAll("\\s"," ");
	}
	public Double getPrice() {
		return price;
	}
	public Double getScreenSize() {
		return screenSize;
	}
	public Double getRam() {
		return ram;
	}
	public String getScreenResolution() {
		return screenResolution;
	}
	public Double getStorageInGB() {
		return storageInGB;
	}
	public Double getCameraResolutionInMP() {
		return cameraResolutionInMP;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public Integer getBatteryInMamp() {
		return batteryInMamp;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public String getNetwork() {
		return network;
	}
	public Integer getWeight(){
		return weight;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batteryInMamp == null) ? 0 : batteryInMamp.hashCode());
		result = prime * result + ((cameraResolutionInMP == null) ? 0 : cameraResolutionInMP.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((network == null) ? 0 : network.hashCode());
		result = prime * result + ((operatingSystem == null) ? 0 : operatingSystem.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((ram == null) ? 0 : ram.hashCode());
		result = prime * result + ((screenResolution == null) ? 0 : screenResolution.hashCode());
		result = prime * result + ((screenSize == null) ? 0 : screenSize.hashCode());
		result = prime * result + ((storageInGB == null) ? 0 : storageInGB.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSchema other = (ProductSchema) obj;
		if (batteryInMamp == null) {
			if (other.batteryInMamp != null)
				return false;
		} else if (!batteryInMamp.equals(other.batteryInMamp))
			return false;
		if (cameraResolutionInMP == null) {
			if (other.cameraResolutionInMP != null)
				return false;
		} else if (!cameraResolutionInMP.equals(other.cameraResolutionInMP))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (network == null) {
			if (other.network != null)
				return false;
		} else if (!network.equals(other.network))
			return false;
		if (operatingSystem == null) {
			if (other.operatingSystem != null)
				return false;
		} else if (!operatingSystem.equals(other.operatingSystem))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (ram == null) {
			if (other.ram != null)
				return false;
		} else if (!ram.equals(other.ram))
			return false;
		if (screenResolution == null) {
			if (other.screenResolution != null)
				return false;
		} else if (!screenResolution.equals(other.screenResolution))
			return false;
		if (screenSize == null) {
			if (other.screenSize != null)
				return false;
		} else if (!screenSize.equals(other.screenSize))
			return false;
		if (storageInGB == null) {
			if (other.storageInGB != null)
				return false;
		} else if (!storageInGB.equals(other.storageInGB))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProductSchema [name=" + name + ", price=" + price + ", screenSize=" + screenSize + ", ram=" + ram
				+ ", screenResolution=" + screenResolution + ", storageInGB=" + storageInGB + ", cameraResolutionInMP="
				+ cameraResolutionInMP + ", operatingSystem=" + operatingSystem + ", batteryInMamp=" + batteryInMamp
				+ ", manufacturer=" + manufacturer + ", network=" + network + ", weight=" + weight + "]";
	}
	
}
