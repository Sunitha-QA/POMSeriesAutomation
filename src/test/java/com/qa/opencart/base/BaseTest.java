package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
//import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CheckOutPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Description;


//@Listeners(ChainTestListener.class)
public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	
	
	protected LoginPage loginpage;
	protected HomePage homepage;
	protected SearchResultsPage searchResultPage;
	protected ProductDetailsPage productpage;
	protected CheckOutPage checkoutPage;
	protected CommonsPage commonspage;
	
	@BeforeTest
	@Parameters({"browser","browserVersion","testname"})
	public void setup(String browserName, String browserVersion, String testname) {
		df=new DriverFactory();
		prop= df.initProp();
		
		if(browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testname);

		}
		driver=df.initDriver(prop);
		loginpage=new LoginPage(driver);
		commonspage = new CommonsPage(driver);
		
		
		ChainPluginService.getInstance().addSystemInfo("Build#","1.0");
		ChainPluginService.getInstance().addSystemInfo("Headless",prop.getProperty("headless"));

		ChainPluginService.getInstance().addSystemInfo("Incognito",prop.getProperty("incognito"));
		ChainPluginService.getInstance().addSystemInfo("Owner","Sunitha Automation Folder");


	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result){
		
		if(!result.isSuccess()) {
		//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		//ChainTestListener.embed(DriverFactory.getScreenshotByte(), "image/png");
		ChainTestListener.embed(DriverFactory.getScreenshotBase64(), "image/png");

		}
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}
	
}
