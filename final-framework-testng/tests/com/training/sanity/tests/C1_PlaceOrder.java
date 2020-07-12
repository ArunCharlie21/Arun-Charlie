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
import com.training.pom.C1PlaceOrderPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Objective of the testcase is to verify whether application allows user to place an order with Pre Logging in & admin change the status of order to complete

public class C1_PlaceOrder {

	private WebDriver driver;
	private String baseUrl;
	private String baseUrl1;
	private LoginPOM loginPOM;
	private C1PlaceOrderPOM c1PlaceOrderPOM;
	private static Properties properties;
	private ScreenShot screenShot;

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
		c1PlaceOrderPOM = new C1PlaceOrderPOM(driver);
		baseUrl1 = properties.getProperty("baseURL1");
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// launching the application 
		driver.get(baseUrl1);
		// passing the credentials to login
		c1PlaceOrderPOM.LoginName("abc@gmail.com");
		c1PlaceOrderPOM.LoginPassword("welcome2ibm");
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test
	public void validLoginTest() throws InterruptedException {

		//user name and password are supplied to the text boxes
		String actualUsername=c1PlaceOrderPOM.getLoginName();
        String expectedUsername="abc@gmail.com";
        //Assertion to Validate whether entered username getting displayed in Username textbox 
        Assert.assertEquals(actualUsername, expectedUsername);
		String actualPassword=c1PlaceOrderPOM.getLoginPassword();
        String expectedPassword="welcome2ibm";
        //Assertion to Validate whether entered password getting displayed in password textbox 
        Assert.assertEquals(actualPassword, expectedPassword);
        //below method clicks the login button
        c1PlaceOrderPOM.clickLoginBtn();
		screenShot.captureScreenShot("Login_Success");
		//below method clicks on the uniform link displayed over screen
		c1PlaceOrderPOM.clickUniform();
		Thread.sleep(500);
		//below method selects the t-shirt
		c1PlaceOrderPOM.clickTshirt();
		//below method to select the size of the t-shirt
		c1PlaceOrderPOM.selectChestSize();
		//below method to add the selected product to cart
		c1PlaceOrderPOM.addToCart();
		//below method to view the cart and checkout the product
		c1PlaceOrderPOM.checkOut();
		//below method continues with billing address checkout
		c1PlaceOrderPOM.continueCheckout();
		//below method continues with shipping checkout
		c1PlaceOrderPOM.continueShipping();
		Thread.sleep(500);
		c1PlaceOrderPOM.addComment("Quality product");
		Thread.sleep(500);
        String actualComment=c1PlaceOrderPOM.getComment();
        String expectedComment="Quality product";
        // assertion to validate Entered comment is same as expected
        Assert.assertEquals(actualComment, expectedComment);
		c1PlaceOrderPOM.continueCashonDelivery();
		Thread.sleep(500);
		screenShot.captureScreenShot("Order Confirmation");
		// launching the admin login application 
		driver.get(baseUrl);
		// passing the credentials to login
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		Thread.sleep(1000);
		String actualUsername1=loginPOM.getUserName();
        String expectedUsername1="admin";
        //Validating whether entered username getting displayed in Username textbox 
        Assert.assertEquals(actualUsername1, expectedUsername1);
		String actualPassword1=loginPOM.getPassword();
        String expectedPassword1="admin@123";
        //Validating whether entered password getting displayed in password textbox 
        Assert.assertEquals(actualPassword1, expectedPassword1);
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("Login_Success");
		c1PlaceOrderPOM.saleClick();
		c1PlaceOrderPOM.saleCheck();
		String actualvalue=c1PlaceOrderPOM.saleCheck();
        String expectedvalue="Orders";
        //Assertion to validate whether the link clicked is same as expected
        Assert.assertEquals(actualvalue, expectedvalue);
		c1PlaceOrderPOM.orderClick();
		//Below method to filter based on customer name
		c1PlaceOrderPOM.sendCustomerName("arun charlie");
		//below method to apply the filter
		c1PlaceOrderPOM.applyFilter();
		String actualcustomer=c1PlaceOrderPOM.customerCheck();
        String expectedcustomer="arun charlie";
        //Assertion to validate whether the customer name is same as expected
        Assert.assertEquals(actualcustomer, expectedcustomer);
		//below method for viewing the order details
		c1PlaceOrderPOM.viewOrder();
		// below method to click on the order status dropdown and select complete
		c1PlaceOrderPOM.orderStatus();
		//below method clicks on add history
		c1PlaceOrderPOM.addHistory();
		Thread.sleep(1000);
		// launching the user login application 
		driver.get(baseUrl1);
		// passing the credentials to login
		c1PlaceOrderPOM.LoginName("abc@gmail.com");
		c1PlaceOrderPOM.LoginPassword("welcome2ibm");
		//click on login button
		c1PlaceOrderPOM.clickLoginBtn();
		Thread.sleep(500);
		//below method to click on view history link
		c1PlaceOrderPOM.clickHistory();
	}
	
}
