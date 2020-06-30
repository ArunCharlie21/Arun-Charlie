package com.training.pom;

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
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/form[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/input[1]")
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
	}


}
