package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;

import io.qameta.allure.Step;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsmanager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").trim().toLowerCase();
		//System.out.println("Browser name is: " +browserName);
		log.info("Browser name is: " +browserName);
		
		optionsmanager =new OptionsManager(prop);

		highlight = prop.getProperty("highlight");
		
		switch (browserName) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
			//driver=new ChromeDriver(optionsmanager.getChromeOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsmanager.getEdgeOptions()));
			//driver=new EdgeDriver(optionsmanager.getEdgeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
			//driver=new FirefoxDriver(optionsmanager.getFirefoxOptions());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			//driver=new SafariDriver();
			break;

		default:
			//System.out.println("pass right browser" +browserName);
			log.error("pass right browser " +browserName);
			throw new FrameworkException("browser not valid");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	//supply env name using maven command line
	//mvn clean install -Denv = qa
	
	public Properties initProp() {
		
		
		String envName = System.getProperty("env");
		//System.out.println("Running test suite in " +envName);
		log.info("Running test suite in " +envName);
		FileInputStream fp=null;
		prop = new Properties();
		try {
		if(envName == null) {
			//System.out.println("no environment is passed hence running test suite in QA environment");
			log.warn("no environment is passed hence running test suite in QA environment");
			fp=new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
		}
		else {
			switch (envName.trim().toLowerCase()) {
			case "dev":
				fp=new FileInputStream(AppConstants.CONFIG_DEV_PROP_FILE_PATH);
				break;
			case "qa":
				fp=new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
				break;
			case "uat":
				fp=new FileInputStream(AppConstants.CONFIG_UAT_PROP_FILE_PATH);
				break;
			case "stage":
				fp=new FileInputStream(AppConstants.CONFIG_STAGE_PROP_FILE_PATH);
				break;
			case "prod":
				fp=new FileInputStream(AppConstants.CONFIG_PROD_PROP_FILE_PATH);
				break;
			default:
				throw new FrameworkException("Invalid Environment");
			}
		}
		
		prop.load(fp);

		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return prop;
	}
	
	
	
	public static String getScreenshot() {
		File srcFile= ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/screenshot/" + "_" +System.currentTimeMillis() + ".png";
		
		File destination = new File(path);
		
		try {
			//FileHandler.copy(srcFile,destination);
			FileUtils.copyFile(srcFile, destination);
		}catch(IOException e) {
			e.printStackTrace();
		}	
		return path;
	}
	
	public static File getScreenshotFile() {
		File srcFile= ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}
	
	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
	}
	
	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
	}
}
