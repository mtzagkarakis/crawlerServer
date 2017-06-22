package gr.aueb.mscis.productCrawlerServer.db.model;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Immutable;

import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.PaginationSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductSelector;
import gr.aueb.mscis.productCrawlerServer.crawler.model.selectors.ProductURLFromPaginationSelector;
import gr.aueb.mscis.productCrawlerServer.utils.BeanValidator;

@Entity
@Table(name="resources")
@Immutable
public class SourceRecord implements BeanValidator<SourceRecord>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	@NotNull
	@Size(min=1, max=250)
	private String name;
	
	@Column(name="category_url")
	@NotNull
	@Size(min=1, max=330)
	private String categoryUrl;
	
	@Column(name="encoding")
	@NotNull
	@Size(min=1, max=30)
	private String encoding;
	
	@Column(name="pagination_selector")
	//@NotNull
	//@Size(min=1, max=250)
	private String paginationSelectorQuery;
	
	@Column(name="pagination_param")
	//@NotNull
	//@Size(min=1, max=250)
	private String paginationUrlParameter;
	
	@Column(name="pagination_step")
	@NotNull
	@Min(1)
	private Integer paginationStep;
	
	@Column(name="product_url")
	@NotNull
	@Size(min=1, max=250)
	private String productFromPaginationPageSelector;
	
	@Column(name="product_name")
	@NotNull
	@Size(min=1, max=250)
	private String productNameSelector;
	
	@Column(name="product_price")
	@NotNull
	@Size(min=1, max=250)
	private String productPriceSelector;
	
	@Column(name="product_price_decimal")
	@NotNull
	@Size(min=1, max=1)
	private String productPriceSelectorDecimalSeperator;
	
	@Column(name="product_manufacturer")
	@NotNull
	@Size(min=0, max=250)
	private String productManufacturerSelector;
	
	@Column(name="product_image")
	@NotNull
	@Size(min=0, max=250)
	private String productImageSelector;
	
	@Column(name="key_selector")
	@NotNull
	@Size(min=1, max=250)
	private String productAttributeKeySelector;
	
	@Column(name="value_selector")
	@NotNull
	@Size(min=1, max=250)
	private String productAttributeValueSelector;
	
	@Column(name="is_active")
	private boolean isActive;
	
	public PaginationSelector getPaginationSelector(){
		return new PaginationSelector(paginationSelectorQuery, paginationUrlParameter, paginationStep);
	}
	public ProductURLFromPaginationSelector getProductURLFromPaginationSelector(){
		return new ProductURLFromPaginationSelector(productFromPaginationPageSelector);
	}
	public ProductSelector getProductSelector(){
		return new ProductSelector()
				.setProductAttributeKeySelector(productAttributeKeySelector)
				.setProductAttributeValueSelector(productAttributeValueSelector)
				.setProductNameSelector(productNameSelector)
				.setProductPriceSelector(productPriceSelector, ",".equalsIgnoreCase(productPriceSelectorDecimalSeperator)?Locale.FRANCE:Locale.US)
				.setProductManufacturerSelector(productManufacturerSelector)
				.setProductImageSelector(productImageSelector);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryUrl() {
		return categoryUrl;
	}
	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getPaginationSelectorQuery() {
		return paginationSelectorQuery;
	}
	public void setPaginationSelectorQuery(String paginationSelectorQuery) {
		this.paginationSelectorQuery = paginationSelectorQuery;
	}
	public String getPaginationUrlParameter() {
		return paginationUrlParameter;
	}
	public void setPaginationUrlParameter(String paginationUrlParameter) {
		this.paginationUrlParameter = paginationUrlParameter;
	}
	public int getPaginationStep() {
		return paginationStep;
	}
	public void setPaginationStep(int paginationStep) {
		this.paginationStep = paginationStep;
	}
	public String getProductFromPaginationPageSelector() {
		return productFromPaginationPageSelector;
	}
	public void setProductFromPaginationPageSelector(String productFromPaginationPageSelector) {
		this.productFromPaginationPageSelector = productFromPaginationPageSelector;
	}
	public String getProductNameSelector() {
		return productNameSelector;
	}
	public void setProductNameSelector(String productNameSelector) {
		this.productNameSelector = productNameSelector;
	}
	public String getProductPriceSelector() {
		return productPriceSelector;
	}
	public void setProductPriceSelector(String productPriceSelector) {
		this.productPriceSelector = productPriceSelector;
	}
	public String getProductPriceSelectorDecimalSeperator() {
		return productPriceSelectorDecimalSeperator;
	}
	public void setProductPriceSelectorDecimalSeperator(String productPriceSelectorDecimalSeperator) {
		this.productPriceSelectorDecimalSeperator = productPriceSelectorDecimalSeperator;
	}
	public String getProductManufacturerSelector() {
		return productManufacturerSelector;
	}
	public void setProductManufacturerSelector(String productManufacturerSelector) {
		this.productManufacturerSelector = productManufacturerSelector;
	}
	public String getProductImageSelector() {
		return productImageSelector;
	}
	public void setProductImageSelector(String productImageSelector) {
		this.productImageSelector = productImageSelector;
	}
	public String getProductAttributeKeySelector() {
		return productAttributeKeySelector;
	}
	public void setProductAttributeKeySelector(String productAttributeKeySelector) {
		this.productAttributeKeySelector = productAttributeKeySelector;
	}
	public String getProductAttributeValueSelector() {
		return productAttributeValueSelector;
	}
	public void setProductAttributeValueSelector(String productAttributeValueSelector) {
		this.productAttributeValueSelector = productAttributeValueSelector;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

	
	
	
	
}
