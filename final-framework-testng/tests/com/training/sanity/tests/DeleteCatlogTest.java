package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CatlogPOM2;
import com.training.pom.DeletePOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Objective of the test case is verify whether application allows the admin to delete a category from list of Categories

public class DeleteCatlogTest {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private DeletePOM deletePOM;
	private CatlogPOM2 catlogPOM2;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		catlogPOM2 = new CatlogPOM2(driver);
		deletePOM = new DeletePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		//launching the application
		driver.get(baseUrl);
		// passing the credentials to login
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		Thread.sleep(1000);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test 
	public void validLoginTest() throws InterruptedException {		
		
		//below method is to hover the mouse over catlog 
		catlogPOM2.catlog();
		// below method is to select the category
		catlogPOM2.category();
		// below method selects the category that needs to be deleted
		deletePOM.catlogSelection();
		Thread.sleep(1000);
		screenShot.captureScreenShot("Category Selected");
		boolean actualResult=deletePOM.catlogValidate();		
		String expectedResult="true";
		// assertion to check if the category checkbox is selected
		Assert.assertTrue(actualResult, expectedResult);
		// below method deleted the selected category
		deletePOM.deletion();
       
	}
}
