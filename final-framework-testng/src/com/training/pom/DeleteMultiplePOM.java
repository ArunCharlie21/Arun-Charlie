package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteMultiplePOM {
private WebDriver driver; 
	
	public DeleteMultiplePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@type='checkbox'][@value='728']")
	private WebElement catalogSelect2; 
	
	@FindBy(xpath="//input[@type='checkbox'][@value='729']")
	private WebElement catalogSelect3; 
	
	
	public void catlogSelection2() {
		this.catalogSelect2.click();
 	}
	
	public boolean catlogValidate2() {
		return this.catalogSelect2.isSelected();
 	}
	
	public void catlogSelection3() {
		this.catalogSelect3.click();
 	}
	
	public boolean catlogValidate3() {
		return this.catalogSelect3.isSelected();
 	}
}
