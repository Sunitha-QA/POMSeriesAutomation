package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
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


@Epic("Epic 100: Design Login page for open cart")
@Story("US 101: Design various features of Login Page functionalities")
@Feature("Feature 102: Login Page features")
public class LoginPageTest extends BaseTest {

	@Description("Verify Login Page Title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Sunitha")
	@Test
	public void LoginPageTitleTest() {
		ChainTestListener.log("Verify Login Page Title");
		String actTitle=loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND_ERROR);
	}
	
	@Description("Verify Login Page URL")
	@Severity(SeverityLevel.MINOR)
	@Test(enabled=false)
	public void LoginPageURLTest() {
		String actURL=loginpage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppErrors.URL_NOT_FOUND_ERROR);
	}
	
	@Description("Verify password link exist")
	@Severity(SeverityLevel.CRITICAL)
	@Test(enabled=false)
	public void fgtPwdExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist(), AppErrors.ELEMENT_NOT_FOUND_ERROR);

	}
	
	@Description("Verify user is able to login to application")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=Integer.MAX_VALUE, enabled=false)
	public void loginTest() {
		homepage =loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND_ERROR);
	}
	
	@Description("Verify logo is displayed")
	@Severity(SeverityLevel.CRITICAL)
	@Test
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
	
	@Description("Verify footer list is displayed")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider="getFooterData", enabled=false)
	public void isFooterListFound(String footerText) {
		
		Assert.assertTrue(commonspage.checkFooterLink(footerText), AppErrors.ELEMENT_NOT_FOUND_ERROR);
	}
}
