package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage  {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}

	
	private By results=By.cssSelector("div#content >h1");
	
	public ProductDetailsPage selectProduct(String productName) {
		eleUtil.doClick(By.linkText(productName));
		return new ProductDetailsPage(driver);
	}
	
	public String checkResultHeader() {
		String resultHeader= eleUtil.doElementGetText(results);
		return resultHeader;
	}
}
