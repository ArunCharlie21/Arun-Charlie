package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatlogPOM2 {
private WebDriver driver; 
	
	public CatlogPOM2(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='fa fa-tags fa-fw']")
	private WebElement catalog; 
	
	@FindBy(xpath="//a[text()='Categories']")
	private WebElement categories;

	
	public void catlog() {
		Actions act=new Actions(driver);
        act.moveToElement(catalog).build().perform();
	}
	
	public String catlogCheck() {
		return this.categories.getText();

	}
	public void category() {
		this.categories.click();
	}

}
