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

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.AddProductPOM;
import com.training.pom.C2ExcelReadPOM;
import com.training.pom.CatlogPOM2;
import com.training.pom.LoginPOM;
import com.training.pom.ProductFilterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Using database - Objective of the test case is to verify whether application displays error message upon entering invalid details while adding product with reward points

public class C3_ErrorValidationDatabase {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private CatlogPOM2 catlogPOM2;
	private ProductFilterPOM productFilterPOM;
	private AddProductPOM addProductPOM;
	private C2ExcelReadPOM c2ExcelReadPOM;

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
		c2ExcelReadPOM = new C2ExcelReadPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// launching the application
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test (dataProvider = "db-inputs", dataProviderClass = LoginDataProviders.class)
	public void validLoginTest(String userName, String password, String productname, String metatag, String model, String price, String quantity, String quantity1, String price1) throws InterruptedException {		
		
		loginPOM.sendUserName(userName);		
		loginPOM.sendPassword(password);
		loginPOM.clickLoginBtn();
		//below method hovers the mouse over catlog
		catlogPOM2.catlog();
		productFilterPOM.product();
		Thread.sleep(1000);
        //below method adds a new product
        addProductPOM.addNewProduct();
        screenShot.captureScreenShot("New Product add screen");
        //below method adds product name
        addProductPOM.sendProductName(productname);
        String actualProduct=addProductPOM.getProductName();
        String expectedProduct="sh";
        // assertion to validate Entered credentials in Product Name of General tab should get displayed
        Assert.assertEquals(actualProduct, expectedProduct);
        Thread.sleep(500);
        //below method adds a new tagtitle
        addProductPOM.sendTagTitle(metatag);
        Thread.sleep(500);
        //below method clicks on data tab
        addProductPOM.ClickData();
        addProductPOM.sendModel(model);
        //below method adds a new price
        addProductPOM.sendPrice(price);
        String actualPrice=addProductPOM.getPrice();
        String expectedPrice="2000";
        // assertion to validate Entered credentials in Price textbox should get displayed
        Assert.assertEquals(actualPrice, expectedPrice);
        Thread.sleep(500);
        //below method adds quantity
        addProductPOM.sendQuantity(quantity);
        String actualQty=addProductPOM.getQuanity();
        String expectedQty="5";
        // assertion to validate Entered credentials in Quantity textbox should get displayed
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
        //below method clicks on add discount button
        c2ExcelReadPOM.addDiscount();
        //below method adds quantity
        c2ExcelReadPOM.addQuantity(quantity1);
        String actualQty1=c2ExcelReadPOM.getQuantity();
        String expectedQty1="800";
        // assertion to validate Entered credentials in Quantity textbox should get displayed
        Assert.assertEquals(actualQty1, expectedQty1);
        Thread.sleep(500);
        //below method adds price
        c2ExcelReadPOM.addPrice(price1);
        //below method sets the start date of the discount
        c2ExcelReadPOM.selectStartDate();
        //below method sets the end date of the discount
        c2ExcelReadPOM.selectEndDate();
        addProductPOM.Special();
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
        screenShot.captureScreenShot("Product addition failure");

	}
}
