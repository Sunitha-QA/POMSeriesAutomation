package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class HomePage {
private WebDriver driver;
private ElementUtil eleUtil;

	public HomePage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);

	}
	
	//private By locators
	private By logoutLink = By.linkText("Logout");
	private By headers= By.cssSelector("div#content>h2");
	private By searchField=By.name("search");
	private By searchButton=By.cssSelector("div#search button");
	
	
	//public page actions
	
	@Step("getHomePageTitle")
	public String getHomePageTitle() {
		String homeTitle= eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE,AppConstants.DEFAULT_TIME);
		System.out.println("Home page title: " +homeTitle);
		return homeTitle;
	}
	
	@Step("getHomePageURL")
	public String getHomePageURL() {
		String homeURL=eleUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME);
		System.out.println("Home page URL: " +homeURL);
		return homeURL;
	}
	
	@Step("logoutApp")
	public boolean isLogoutDisplayed() {
		return eleUtil.doIsElementDisplayed(logoutLink);
	}
	
	@Step("logoutApp is displayed")
	public void logoutApp() {
		if(isLogoutDisplayed()) {
			eleUtil.doClick(logoutLink);
		}
		//pending
	}
	
	@Step("findHeaderElements")
	public List<String> findHeaderElements() {
		List<WebElement> headersList = eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_TIME);
		List<String> headersValueList=new ArrayList<String>();
		for(WebElement e:headersList) {
			String text=e.getText();
			headersValueList.add(text);
		}
		return headersValueList;
	}
	
	@Step("searchField using {0}")
	public SearchResultsPage searchField(String searchKey) {
		System.out.println("Search key: " +searchKey);
		WebElement searchEle = eleUtil.waitForElementVisible(searchField, AppConstants.DEFAULT_TIME);
		eleUtil.doSendKeys(searchEle, searchKey);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}
	

}
