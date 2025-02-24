package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Product Result page for open cart")
@Story("US 101: Design various features of products Page functionalities")
@Feature("Feature 102: products Page features")
public class ProductResultsTest extends BaseTest {

	
	@BeforeClass
	public void SearchPageSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
					
	}
	
	
	@DataProvider
	public Object[][] getProductData() {
		
		return new Object[][] {
			{"macbook","MacBook Air"},	
			{"imac","iMac"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"canon","Canon EOS 5D"},
		};
	}
	
	@DataProvider
	public Object[][] getImageCount() {
		
		return new Object[][] {
			{"macbook","MacBook Air",4},	
			{"imac","iMac",3},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"canon","Canon EOS 5D",3},
		};
	}
	
	
	@DataProvider
	public Object[][] getImageSheetData() {
		Object productData[][] = ExcelUtil.getExcelTestData(AppConstants.PRODUCT_TEST_DATA_SHEET_NAME);
		return productData;
	}
	
	@Description("Verify user is able to select product")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductData" )
	public void selectAProduct(String productName,String productValue) {
		searchResultPage=homepage.searchField(productName);
		productpage = searchResultPage.selectProduct(productValue);
		String resultText=productpage.getProductHeader();
		Assert.assertEquals(resultText, productValue);
	}
	
	@Description("Verify total image count{2} for a product {1}")
	@Severity(SeverityLevel.MINOR)
	@Test(dataProvider = "getImageSheetData" )
	public void testImageCount(String productName,String productValue,String productImageCount) {
		searchResultPage=homepage.searchField(productName);
		productpage = searchResultPage.selectProduct(productValue);
		int imageCount=productpage.getImagesCount();
		Assert.assertEquals(imageCount, Integer.parseInt(productImageCount));
	}
	
	@Description("Verify full product info")
	@Severity(SeverityLevel.MINOR)
	@Test 
	public void getFullProductInfo() {
		searchResultPage=homepage.searchField("macbook");
		productpage = searchResultPage.selectProduct("MacBook Air");
		
		Map<String,String> productMap = productpage.getProductInfo();
		
		productMap.forEach((k,v) -> System.out.println(k +":" +v) );
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertEquals(productMap.get("Header"), "MacBook Air");
		softAssert.assertEquals(productMap.get("Brand"), "Apple");
		softAssert.assertEquals(productMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productMap.get("Product Code"),"Product 17");
		softAssert.assertEquals(productMap.get("Price"),"$1,202.00");
		softAssert.assertEquals(productMap.get("Tax"),"$1,000.00");

		softAssert.assertAll();
	}
	
	
	@DataProvider
	public Object[][] getCartItems() {
		
		return new Object[][] {
			{"macbook","MacBook Air","4"},	
//			{"imac","iMac","3"},
//			{"samsung","Samsung Galaxy Tab 10.1","2"},
		};
	}
	
	@Description("Verify product {1} is added to cart")
	@Severity(SeverityLevel.MINOR)
	@Test (priority=Integer.MAX_VALUE,dataProvider = "getCartItems" )
	public void addItemToCart(String productName,String productValue,String quantity) {
		searchResultPage=homepage.searchField(productName);
		productpage = searchResultPage.selectProduct(productValue);
		
		boolean actalFlag = productpage.addQtyAndAddToCart(quantity);
		Assert.assertEquals(actalFlag, true);
		checkoutPage = productpage.viewCart();
		String actualText = checkoutPage.getCheckOutPageTitle();
		System.out.println(actualText);
		Assert.assertEquals(actualText, AppConstants.CHECKOUT_PAGE_TITLE);

	}
}
