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
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

//Objective of the test case is to verify whether application allows admin to get logged in by entering valid credentials in required field

public class S1_Login {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
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
	
	@Test
	public void validLoginTest() throws InterruptedException {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		//user name and password are supplied to the text boxes
		Thread.sleep(1000);
		String actualUsername=loginPOM.getUserName();
        String expectedUsername="admin";
        //Validating whether entered username getting displayed in Username textbox 
        Assert.assertEquals(actualUsername, expectedUsername);
		String actualPassword=loginPOM.getPassword();
        String expectedPassword="admin@123";
        //Validating whether entered password getting displayed in password textbox 
        Assert.assertEquals(actualPassword, expectedPassword);
        //below method clicks the login button
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("Login_Success");
	}
}
