package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//1. By locator :page objects : OR
	
	private By emailId= By.id("input-email");
	private By password= By.id("input-password");
	private By loginButton= By.xpath("//input[@value='Login']");
	private By forgottenPwd= By.linkText("Forgotten Password11");

	//2. public page actions - methods features
	@Step("getLoginPageTitle")
	public String getLoginPageTitle() {
		String title= eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,AppConstants.DEFAULT_TIME);
		System.out.println("login page title: " +title);
		return title;
	}
	
	@Step("getLoginPageURL")
	public String getLoginPageURL() {
		String loginURL=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME);
		System.out.println("login page URL: " +loginURL);
		return loginURL;
	}
	
	@Step("isForgotPwdLinkExist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsElementDisplayed(forgottenPwd);
	}
	
	@Step("doLogin with username {0} and password {1}")
	public HomePage doLogin(String userName, String pwd) {
		System.out.println("aplication credentials are "+userName +" : " +pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.MIN_TIMEOUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return new HomePage(driver);
	}
}
