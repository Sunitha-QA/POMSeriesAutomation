package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class CommonsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public CommonsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);

	}
	
	
	private By logo= By.xpath("//img[@title='naveenopencart']");
	
	private By foolterLinks= By.xpath("//footer//a");
	
	public boolean isLogoDisplayed() {
		return eleUtil.isElementDisplayed(logo);
	}
	
	public List<String> getFootersList() {
		List<WebElement> listFooters = eleUtil.waitForElementsPresence(foolterLinks, AppConstants.DEFAULT_TIME);
		
		System.out.println("Number of footers: " +listFooters.size());
		
		List<String> footerText= new ArrayList<String>();
		for(WebElement e: listFooters) {
			String text = e.getText();
			footerText.add(text);
			
		}
		return footerText;
	}
	
	public boolean checkFooterLink(String footerText) {
		return getFootersList().contains(footerText);
	}
}
