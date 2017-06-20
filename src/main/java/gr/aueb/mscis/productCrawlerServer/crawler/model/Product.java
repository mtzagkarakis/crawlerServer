package gr.aueb.mscis.productCrawlerServer.crawler.model;

import java.util.HashMap;
import java.util.Map;

public class Product {
	private final String url, imageUrl, name, manufacturer;
	private final Double price;
	private final Map<String, String> attributes;
	
	public static final class Builder{
		private String url = "";
		private String imageUrl = "";
		private String manufacturer = "";
		private String name = "";
		private Double price = 0.0d;
		private Map<String, String> attributes = new HashMap<>();
		public Builder setUrl(String url) {
			this.url = url; return this;
		}
		
		public String getUrl() {return url;}

		public String getImageUrl() {return imageUrl;}

		public String getManufacturer() {return manufacturer;}

		public String getName() {return name;}

		public Double getPrice() {return price;}

		public Map<String, String> getAttributes() {return attributes;}

		public Builder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl; return this;
		}
		public Builder setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer; return this;
		}
		public Builder setName(String name) {
			this.name = name; return this;
		}
		public Builder setPrice(Double price) {
			this.price = price; return this;
		}
		public Builder setAttributes(Map<String, String> attributes) {
			this.attributes = attributes; return this;
		}
		public Product build(){
			return new Product(this);
		}
		
	}
	public Product(Builder builder) {
		super();
		this.url = builder.getUrl();
		this.imageUrl = builder.getImageUrl();
		this.name = builder.getName();
		this.manufacturer = builder.getManufacturer();
		this.price = builder.getPrice();
		this.attributes = builder.getAttributes();
	}
	public Product(Product product){
		this.url = product.getUrl();
		this.imageUrl = product.getImageUrl();
		this.name = product.getName();
		this.manufacturer = product.getManufacturer();
		this.price = product.getPrice();
		this.attributes = product.getAttributes();
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	public String getUrl() {
		return url;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Product [name=" + name + ", price=" + price + ", url="+url+ ", imageUrl="+imageUrl + ", manufacturer="+manufacturer)
		.append(", attributes= ");
		attributes.forEach((key, value)->sb.append("\n"+key+" -> "+value));
		sb.append("\n]");
		return sb.toString();
	}
	
}
