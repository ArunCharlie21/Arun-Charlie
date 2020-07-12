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

// Objective of the test case is to verify whether application allows admin to add multiple product with the rewards point

public class C2_AddMultipleProduct {
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
	
	@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void validLoginTest(String productName, String metatag, String model, String price, String quantity, String quanity1, String price1,String points) throws InterruptedException {	
			
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
        screenShot.captureScreenShot("New Product add screen");
        //below method adds product name
        addProductPOM.sendProductName(productName);
        String actualProduct=addProductPOM.getProductName();
        String expectedProduct="Brown T shirt";
        // assertion to validate Entered credentials in Product Name of General tab should get displayed
        Assert.assertEquals(actualProduct, expectedProduct);
        Thread.sleep(500);
        //below method adds a new tagtitle
        addProductPOM.sendTagTitle(metatag);
        String actualTag=addProductPOM.getTagTitle();
        String expectedTag="T shirt";
        // assertion to validate Entered credentials in Tag title of General tab should get displayed
        Assert.assertEquals(actualTag, expectedTag);
        Thread.sleep(500);
        screenShot.captureScreenShot("General tab records");
        //below method clicks on data tab
        addProductPOM.ClickData();
        addProductPOM.sendModel(model);
        String actualModel=addProductPOM.getModel();
        String expectedModel="RTS-012";
        // assertion to validate Entered credentials in Model textbox should get displayed
        Assert.assertEquals(actualModel, expectedModel);
        //below method adds a new price
        addProductPOM.sendPrice(price);
        String actualPrice=addProductPOM.getPrice();
        String expectedPrice="1500.0";
        // assertion to validate Entered credentials in Price textbox should get displayed
        Assert.assertEquals(actualPrice, expectedPrice);
        Thread.sleep(500);
        //below method adds quantity
        addProductPOM.sendQuantity(quantity);
        String actualQty=addProductPOM.getQuanity();
        String expectedQty="50.0";
        // assertion to validate Entered credentials in Quantity textbox should get displayed
        Assert.assertEquals(actualQty, expectedQty);
        Thread.sleep(500);
        //below method clicks on Discount tab
        addProductPOM.Discount();
        //below method clicks on add discount button
        c2ExcelReadPOM.addDiscount();
        c2ExcelReadPOM.addQuantity(quanity1);
        String actualQty1=c2ExcelReadPOM.getQuantity();
        String expectedQty1="3.0";
        // assertion to validate Entered credentials in Quantity textbox should get displayed
        Assert.assertEquals(actualQty1, expectedQty1);
        Thread.sleep(500);
        c2ExcelReadPOM.addPrice(price1);
        String actualPrice1=c2ExcelReadPOM.getPrice();
        String expectedPrice1="800.0";
        // assertion to validate Entered credentials in Price textbox should get displayed
        Assert.assertEquals(actualPrice1, expectedPrice1);
        //below method sets the start date of the discount
        c2ExcelReadPOM.selectStartDate();
        //below method sets the end date of the discount
        c2ExcelReadPOM.selectEndDate();
        //below method clicks on Special tab
        addProductPOM.Special();
        //below method clicks on Image tab
        addProductPOM.Image();
        Thread.sleep(500);
        //below method clicks on Reward tab
        addProductPOM.Reward();
        c2ExcelReadPOM.addPoints(points);
        String actualPoints=c2ExcelReadPOM.getPoints();
        String expectedPoints="30.0";
        // assertion to validate entered credentials in Points textbox should get displayed
        Assert.assertEquals(actualPoints, expectedPoints);
        //below method clicks on Design tab        
        addProductPOM.Design();
        //below method clicks on Save
        addProductPOM.Save();
        screenShot.captureScreenShot("Product added sucessfully");

	}
}
