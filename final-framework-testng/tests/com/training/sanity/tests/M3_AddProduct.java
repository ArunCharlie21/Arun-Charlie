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
import com.training.pom.AddProductPOM;
import com.training.pom.CatlogPOM2;
import com.training.pom.LoginPOM;
import com.training.pom.ProductFilterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Objective of the test case is to verify whether application allows admin to add product by entering valid credentials in mandatory fields only

public class M3_AddProduct {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private CatlogPOM2 catlogPOM2;
	private ProductFilterPOM productFilterPOM;
	private AddProductPOM addProductPOM; 

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
		addProductPOM = new AddProductPOM(driver);
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
        //below method adds a new product
        addProductPOM.addNewProduct();
        //below method adds product name
        addProductPOM.sendProductName("Charlie jeans");
        String actualProduct=addProductPOM.getProductName();
        String expectedProduct="Charlie jeans";
        Assert.assertEquals(actualProduct, expectedProduct);
        Thread.sleep(500);
        //below method adds a new tagtitle
        addProductPOM.sendTagTitle("Spl Edition");
        String actualTag=addProductPOM.getTagTitle();
        String expectedTag="Spl Edition";
        Assert.assertEquals(actualTag, expectedTag);
        Thread.sleep(500);
        //below method clicks on data tab
        addProductPOM.ClickData();
        addProductPOM.sendModel("SHG-010");
        String actualModel=addProductPOM.getModel();
        String expectedModel="SHG-010";
        Assert.assertEquals(actualModel, expectedModel);
        //below method adds a new price
        addProductPOM.sendPrice("750");
        String actualPrice=addProductPOM.getPrice();
        String expectedPrice="750";
        Assert.assertEquals(actualPrice, expectedPrice);
        Thread.sleep(500);
        //below method adds quantity
        addProductPOM.sendQuantity("50");
        String actualQty=addProductPOM.getQuanity();
        String expectedQty="50";
        Assert.assertEquals(actualQty, expectedQty);
        Thread.sleep(500);
        //below method clicks on Links tab
        addProductPOM.ClickLinks();
        Thread.sleep(500);
        //below method clicks on categories
        addProductPOM.ClickCategories();
        //below method clicks on Attribute tab
        addProductPOM.Attribute();
        //below method clicks on Option tab
        addProductPOM.Option();
        //below method clicks on Recurring tab
        addProductPOM.Recurring();
        //below method clicks on Discount tab
        addProductPOM.Discount();
        //below method clicks on Special tab
        addProductPOM.Special();
        //below method clicks on Image tab
        addProductPOM.Image();
        Thread.sleep(500);
        //below method clicks on Reward tab
        addProductPOM.Reward();
        //below method clicks on Design tab
        addProductPOM.Design();
        //below method clicks on Save
        addProductPOM.Save();

	}
}
