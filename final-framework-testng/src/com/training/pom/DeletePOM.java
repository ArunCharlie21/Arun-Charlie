package com.training.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeletePOM {
private WebDriver driver; 
	
	public DeletePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@type='checkbox'][@value='933']")
	private WebElement catalogSelect; 
	
	@FindBy(xpath="//button[@class='btn btn-danger']")
	private WebElement deleteCat;

	
	public void catlogSelection() {
		this.catalogSelect.click();
 	}
	
	public boolean catlogValidate() {
		return this.catalogSelect.isSelected();
 	}
	
	public void deletion() {
		this.deleteCat.click();
        Alert alertpop=driver.switchTo().alert();
        alertpop.accept();
	}


}
