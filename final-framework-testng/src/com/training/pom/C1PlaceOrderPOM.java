package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class C1PlaceOrderPOM {

	private WebDriver driver; 
	
	public C1PlaceOrderPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	private WebElement userName; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//input[@type='submit'][@value='Login']")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//a[text()='Uniform Store']")
	private WebElement uniformclk;
	
	@FindBy(xpath="//img[@title='REGULAR T-SHIRTS (Rust)']")
	private WebElement tshirtclk;
	
	@FindBy(xpath="//select[@name='option[376]']")
	private WebElement chestsize;
	
	@FindBy(xpath="//button[text()='Add to Cart']")
	private WebElement addcart;
	
	@FindBy(id="cart-total")
	private WebElement viewcart;
	
	@FindBy(xpath="//*//text()[contains(.,'Checkout')]/ancestor::strong[1]")
	private WebElement checkout;
	
	@FindBy(id="button-payment-address")
	private WebElement continuecheckout;
	
	@FindBy(id="button-shipping-address")
	private WebElement continueshipping;
	
	@FindBy(xpath="//textarea[@name='comment']")
	private WebElement addcomment;
	
	@FindBy(id="button-shipping-method")
	private WebElement continueCOD;
	
	@FindBy(xpath="//input[@type='checkbox'][@name='agree']")
	private WebElement checkboxDeliveryinfo;
	
	@FindBy(id="button-confirm")
	private WebElement confirmOrder;
	
	@FindBy(xpath="//*[@id=\"sale\"]/a")
	private WebElement sale; 
	
	@FindBy(xpath="//*[@id=\"sale\"]/ul/li[1]/a")
	private WebElement orders;
	
	@FindBy(id="input-customer")
	private WebElement customer;
		
	@FindBy(id="button-filter")
	private WebElement filter;
	
	@FindBy(xpath="//tr[1]//td[8]//*[@class='fa fa-eye']")
	private WebElement vieworder;
	
	@FindBy(id="input-order-status")
	private WebElement orderstatus;
	
	@FindBy(id="button-history")
	private WebElement history;
	
	@FindBy(xpath="//a[text()='View your order history']")
	private WebElement historycheck;
	
	
		
	public void LoginName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public String getLoginName() {
		return this.userName.getAttribute("value");
	}
	
	public String getLoginPassword() {
		return this.password.getAttribute("value");
	}
	
	public void LoginPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	public void clickUniform() {
		this.uniformclk.click(); 
	}
	
	public void clickTshirt() {
		this.tshirtclk.click(); 
	}
	
	public void selectChestSize() {
		Select selectValue= new Select(chestsize);
        selectValue.selectByIndex(1);
	}
	
	public void addToCart() {
		this.addcart.click(); 
	}

	public void checkOut() {
		Actions act=new Actions(driver);
		act.moveToElement(viewcart).click().perform();
        act.moveToElement(checkout).build().perform();
		act.click().perform();
	}
	
	public void continueCheckout() {
		this.continuecheckout.click(); 
	}
	
	public void continueShipping() {
		this.continueshipping.click(); 
	}
	
	public void addComment(String addcomment) {
		this.addcomment.clear();
		this.addcomment.sendKeys(addcomment);
	}
	
	public String getComment() {
		return this.addcomment.getAttribute("value");
	}
	
	public void continueCashonDelivery() {
		this.continueCOD.click(); 
	}
	
	public void enableCheckbox() {
		this.checkboxDeliveryinfo.click();
	}
	
	public void confirmOrder() {
		this.confirmOrder.click();
	}
	
	public void saleClick() {
		Actions act=new Actions(driver);
        act.moveToElement(sale).build().perform();
	}
	
	public String saleCheck() {
		return this.orders.getText();
	}
	
	public void orderClick() {
		this.orders.click();
	}
	
	public void sendCustomerName(String customer) {
		this.customer.clear();
		this.customer.sendKeys(customer);
	}
	
	public String customerCheck() {
		return this.customer.getAttribute("value");
	}
	
	public void applyFilter() {
		this.filter.click(); 
	}
	
	public void viewOrder() {
		this.vieworder.click(); 
	}
	
	public void orderStatus() {
		this.orderstatus.click();
        Select selectValue= new Select(orderstatus);
        selectValue.selectByVisibleText("Complete");
	}	
	
	public void addHistory() {
		this.history.click(); 
	}
	
	public void clickHistory() {
		this.historycheck.click(); 
	}
	
}
