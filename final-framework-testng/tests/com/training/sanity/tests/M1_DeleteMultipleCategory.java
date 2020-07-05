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
import com.training.pom.DeleteMultiplePOM;
import com.training.pom.DeletePOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Objective of this test case is to verify whether application allows admin to delete multiple category from categories list

public class M1_DeleteMultipleCategory {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private DeletePOM deletePOM;
	private CatlogPOM2 catlogPOM2;
	private DeleteMultiplePOM deleteMultiplePOM;

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
		deleteMultiplePOM = new DeleteMultiplePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// launching the application
		driver.get(baseUrl);
		//passing the credentials to login
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test 
	public void validLoginTest() throws InterruptedException {		
		
		//below method hovers the mouse over catlog
		catlogPOM2.catlog();
		//below method clicks on the category
		catlogPOM2.category();
		//below method selects a category
		deleteMultiplePOM.catlogSelection2();
		Thread.sleep(1000);
		screenShot.captureScreenShot("Category Selected2");
		boolean actualResult2=deleteMultiplePOM.catlogValidate2();		
		String expectedResult2="true";
		//assert to check if category is selected
		Assert.assertTrue(actualResult2, expectedResult2);
		Thread.sleep(1000);
		//below method selects a category
		deleteMultiplePOM.catlogSelection3();
		Thread.sleep(1000);
		screenShot.captureScreenShot("Category Selected3");
		boolean actualResult3=deleteMultiplePOM.catlogValidate3();		
		String expectedResult3="true";
		//assert to check if category is selected
		Assert.assertTrue(actualResult3, expectedResult3);
		//below method deletes the selected category 
		deletePOM.deletion();
       
	}
}
