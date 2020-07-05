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
import com.training.pom.ProductFilterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Objective of the test case is to verify whether application allows the admin to filter the product details with all textbox

public class M2_ApplyFilter {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private CatlogPOM2 catlogPOM2;
	private ProductFilterPOM productFilterPOM; 

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
		productFilterPOM = new ProductFilterPOM(driver);
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
		Thread.sleep(1000);
		String actualResult1=productFilterPOM.productCheck();
		//below method clicks on the product
		productFilterPOM.product();
        String expectedResult1="Products";
        //validating whether product is displayed
        Assert.assertEquals(actualResult1, expectedResult1);
        screenShot.captureScreenShot("Product Selected");
        
		//below method passes value to Product Name textbox
		productFilterPOM.sendProductName("Regular");
		//below method clicks on filter
		productFilterPOM.applyFilter();
		String actualProductname=productFilterPOM.getProductName();
        String expectedProductname="Regular";
        //Validating whether entered product name getting displayed in product name textbox 
        Assert.assertEquals(actualProductname, expectedProductname);
        screenShot.captureScreenShot("Filter on Product Name");
        
		//below method passes value to Price textbox
		productFilterPOM.sendPrice("540");
		Thread.sleep(500);
		//below method clicks on filter
		productFilterPOM.applyFilter();
		String actualPrice=productFilterPOM.getPrice();
        String expectedPrice="540";
        //Validating whether entered price getting displayed in price textbox 
        Assert.assertEquals(actualPrice, expectedPrice);
        screenShot.captureScreenShot("Filter on Price");
		
		//below method selects value from dropdown
		productFilterPOM.selectStatus();
		Thread.sleep(500);
		//below method clicks on filter
		productFilterPOM.applyFilter();
		String actualStatus=productFilterPOM.getStatus();
        String expectedStatus="1";
        //Validating whether selected status getting displayed in Status dropdown 
        Assert.assertEquals(actualStatus, expectedStatus);
        screenShot.captureScreenShot("Filter on Status");
		
		//below method passes value to Model textbox
		productFilterPOM.sendModel("TBSM");
		Thread.sleep(500);
		//below method clicks on filter
		productFilterPOM.applyFilter();
		String actualModel=productFilterPOM.getModel();
        String expectedModel="TBSM";
        //Validating whether entered Model getting displayed in Model textbox 
        Assert.assertEquals(actualModel, expectedModel);
        screenShot.captureScreenShot("Filter on Model");
		
		//below method passes value to quantity textbox
		productFilterPOM.sendQuanity("40");
		Thread.sleep(500);
		//below method clicks on filter
		productFilterPOM.applyFilter();
		String actualQuanity=productFilterPOM.getQuanity();
        String expectedQuanity="40";
        //Validating whether entered Qunaity getting displayed in Quantity textbox 
        Assert.assertEquals(actualQuanity, expectedQuanity);
        screenShot.captureScreenShot("Filter on Quanity");

	}
}
