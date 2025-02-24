package com.qa.opencart.pages;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class CheckOutPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

		public CheckOutPage(WebDriver driver) {
			this.driver=driver;
			eleUtil = new ElementUtil(driver);
		}
		
		
		public String getCheckOutPageTitle() {
			String checkoutPageTitle= eleUtil.waitForTitleIs(AppConstants.CHECKOUT_PAGE_TITLE,AppConstants.DEFAULT_TIME);
			System.out.println("CheckOut page title: " +checkoutPageTitle);
			return checkoutPageTitle;
		}
		
		
}
