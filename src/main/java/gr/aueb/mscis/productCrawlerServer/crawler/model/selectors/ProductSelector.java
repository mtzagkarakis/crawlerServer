package gr.aueb.mscis.productCrawlerServer.crawler.model.selectors;

import java.util.Locale;

public class ProductSelector extends AbstractSelector{
	
	private String productAttributeKey=null, productAttributeValue=null, productName=null, productManufacturer=null, productImage=null;
	private ProductPriceSelector productPrice = new ProductPriceSelector();
	public ProductSelector setProductAttributeKeySelector(String productAttributeKey) {
		this.productAttributeKey = productAttributeKey;
		return this;
	}

	public ProductSelector setProductAttributeValueSelector(String productAttributeValue) {
		this.productAttributeValue = productAttributeValue;
		return this;
	}

	public ProductSelector setProductNameSelector(String productName) {
		this.productName = productName;
		return this;
	}
	
	public ProductSelector setProductManufacturerSelector(String productManufacturer) {
		this.productManufacturer = productManufacturer;
		return this;
	}
	public ProductSelector setProductImageSelector(String productImage) {
		this.productImage = productImage;
		return this;
	}
	public ProductSelector setProductPriceSelector(ProductPriceSelector productPrice) {
		this.productPrice = productPrice;
		return this;
	}
	
	public ProductSelector setProductPriceSelector(String selector, Locale locale) {
		this.productPrice.priceSelector = selector;
		this.productPrice.locale= locale;
		return this;
	}

	public String getProductAttributeKeySelector() {
		return productAttributeKey;
	}

	public String getProductAttributeValueSelector() {
		return productAttributeValue;
	}

	public String getProductNameSelector() {
		return productName;
	}
	
	public String getProductManufacturerSelector(){
		return productManufacturer;
	}

	public String getProductPriceSelector() {
		return productPrice.priceSelector;
	}
	public Locale getProductPriceLocale() {
		return productPrice.locale;
	}
	public String getProductImageSelector(){
		return productImage;
	}
	
}
