package com.qa.opencart.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Home page for open cart")
@Story("US 101: Design various features of Home Page functionalities")
@Feature("Feature 102: Home Page features")
public class HomePageTest extends BaseTest{



	@BeforeClass
	public void homePageSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("Verify Home Page Title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Sunitha")
	@Test
	public void HomePageTitleTest() {
		String homeTitle=homepage.getHomePageTitle();
		Assert.assertEquals(homeTitle, AppConstants.HOME_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND_ERROR);
	}
	
	@Description("Verify Home Page URL")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void HomePageURLTest() {
		String homeURL=homepage.getHomePageURL();
		Assert.assertTrue(homeURL.contains(AppConstants.HOME_PAGE_URL_FRACTION), AppErrors.URL_NOT_FOUND_ERROR);
	}
	
	@Description("Verify Headers are displayed")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void HeadersTest() {
		List<String> actualHeaders=homepage.findHeaderElements();
		System.out.println("Headers: " +actualHeaders);
		
	}
	
	@Description("Verify logout is displayed")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void logoutIsPresent() {
		Assert.assertTrue(homepage.isLogoutDisplayed(),AppErrors.ELEMENT_NOT_FOUND_ERROR);
	}
	
	
	
	@DataProvider
	public Object[][] getSearchData() {
		
		return new Object[][] {
			{"macbook","Search - macbook"},
			{"imac","Search - imac"},
			{"samsung","Search - samsung"},
			{"canon","Search - canon"},

			
		};
	}
	
	@Description("Verify search product is working")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getSearchData" )
	public void searchTest(String searchKey, String resultkey) {
		searchResultPage = homepage.searchField(searchKey);
		String  resultText=searchResultPage.checkResultHeader();
		Assert.assertEquals(resultText, resultkey);
	}
	
	@Description("Verify logo is displayed")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled = false)
	public void isLogoDisplayed() {
		Assert.assertTrue(commonspage.isLogoDisplayed(), AppErrors.LOGO_NOT_FOUND_ERROR);
	}
	
	
	@DataProvider
	public Object[][] getFooterData() {
		
		return new Object[][] {
			{"About Us"},
			{"Contact Us"},
			{"Specials"},
			{"Wish List"}
		};
	}
	
	@Description("Get footer links")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider="getFooterData",enabled = false)
	public void isFooterListFound(String footerText) {
		
		Assert.assertTrue(commonspage.checkFooterLink(footerText), AppErrors.ELEMENT_NOT_FOUND_ERROR);
	}
}
