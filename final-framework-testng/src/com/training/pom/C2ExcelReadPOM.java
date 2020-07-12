package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class C2ExcelReadPOM {
	
	private WebDriver driver; 
	
	public C2ExcelReadPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	private WebElement userName; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//*[@id=\"discount\"]/tfoot/tr/td[2]/button")
	private WebElement addDiscount; 
	
	@FindBy(xpath="//input[@type='text'][@name='product_discount[0][quantity]']")
	private WebElement quantity;
	
	@FindBy(xpath="//input[@type='text'][@name='product_discount[0][price]']")
	private WebElement price;
	
	@FindBy(xpath="//td[5]//*[@class='fa fa-calendar']")
	private WebElement startdate;	
	
	@FindBy(xpath="//div[5]//tr[3]//td[4][@class='day']")
	private WebElement selectstartdate;	
	
	@FindBy(xpath="//td[6]//*[@class='fa fa-calendar']")
	private WebElement enddate;	
	
	@FindBy(xpath="//div[6]//tr[5]//td[6][@class='day']")
	private WebElement selectenddate;	
	
	@FindBy(id="input-points")
	private WebElement points;
	
	public void addDiscount() {
		this.addDiscount.click(); 
	}
		
	public void addQuantity(String quantity) {
		this.quantity.clear();
		this.quantity.sendKeys(quantity);
	}
	
	public String getQuantity() {
		return this.quantity.getAttribute("value");
	}
	
	
	public void addPrice(String price) {
		this.price.clear(); 
		this.price.sendKeys(price); 
	}
	
	public String getPrice() {
		return this.price.getAttribute("value");
	}


	public void selectStartDate() {
		// method to click on date and adding the date
		Actions act=new Actions(driver);
		act.moveToElement(startdate).click().perform();
        act.moveToElement(selectstartdate).build().perform();
		act.click().perform();
	}
	
	public void selectEndDate() {
		Actions act=new Actions(driver);
		act.moveToElement(enddate).click().perform();
        act.moveToElement(selectenddate).build().perform();
		act.click().perform();
	}
	
	public void addPoints(String price) {
		this.points.clear();
		this.points.sendKeys(price);
	}
	
	public String getPoints() {
		return this.points.getAttribute("value");
	}

}
