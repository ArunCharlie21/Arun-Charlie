package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductFilterPOM {
private WebDriver driver; 
	
	public ProductFilterPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Products']")
	private WebElement products;
	
	@FindBy(id="input-name")
	private WebElement productname;
	
	@FindBy(id="input-price")
	private WebElement price;
	
	@FindBy(id="input-status")
	private WebElement status;
	
	@FindBy(id="input-model")
	private WebElement model;
	
	@FindBy(id="input-quantity")
	private WebElement quantity;
	
	@FindBy(id="button-filter")	
	private WebElement filter; 
	
	public void product() {
		this.products.click();
	}
	
	public String productCheck() {
		return this.products.getText();
	}
	
	public void sendProductName(String productname) {
		this.productname.clear();
		this.productname.sendKeys(productname);
	}
	
	public String getProductName() {
		return this.productname.getAttribute("value");	
	}
	
	public void sendPrice(String price) {
		this.price.clear();
		this.price.sendKeys(price);
	}
	
	public String getPrice() {
		return this.price.getAttribute("value");	
	}
	
	public void selectStatus() {
		Select selectValue= new Select(status);
        selectValue.selectByVisibleText("Enabled");
	}
	
	
	public String getStatus() {
		return this.status.getAttribute("value");	
	}
	
	public void sendModel(String model) {
		this.model.clear();
		this.model.sendKeys(model);
	}
	
	public String getModel() {
		return this.model.getAttribute("value");	
	}
	
	public void sendQuanity(String quantity) {
		this.quantity.clear();
		this.quantity.sendKeys(quantity);
	}
	
	public String getQuanity() {
		return this.quantity.getAttribute("value");	
	}
	
	public void applyFilter() {
		this.filter.click(); 
	}


}
