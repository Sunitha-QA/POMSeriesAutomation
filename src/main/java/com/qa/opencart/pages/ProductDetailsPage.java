package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class ProductDetailsPage {

private WebDriver driver;
private ElementUtil eleUtil;
private Map<String, String> productMap;

	public ProductDetailsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);

	}
	
	private By productHeader=By.tagName("h1");
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productMetadata= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By qtyAmount = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By viewCart = By.id("cart-total");
	private By successMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By viewCartButton = By.xpath("//p[@class='text-right']//a/strong[contains(text(),'Cart')]");
	
	@Step("getProductHeader")
	public String getProductHeader() {
		String productText= eleUtil.doElementGetText(productHeader);
		return productText;
	}
	
	@Step("getImagesCount")
	public int getImagesCount() {
		int count=eleUtil.waitForElementsPresence(productImages, AppConstants.DEFAULT_TIME).size();
		System.out.println(getProductHeader() +" Images count: " +count);
		return count;
	}
	
	@Step("getProductInfo")
	public Map<String,String> getProductInfo() {
		productMap = new HashMap<String, String>();
		//productMap = new LinkedHashMap<String, String>();

		//productMap = new TreeMap<String, String>();
		productMap.put("Header", getProductHeader());
		productMap.put("ImagesCount", getImagesCount()+"");
		getProductMetaData();
		getProductPriceData();
		
		return productMap;
	}
	
	//Brand: Apple
	//Product Code: Product 14
	//Availability: Out Of Stock
	
	@Step("getProductMetaData")
	private void getProductMetaData() {

		List<WebElement> prodMetaList = eleUtil.waitForElementsPresence(productMetadata, AppConstants.DEFAULT_TIME);
		for(WebElement e:prodMetaList ) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue= meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
		
	}
	
	//$122.00
	//Ex Tax: $100.00
	@Step("get product price")
	private void getProductPriceData() {

		List<WebElement> prodPriceList = eleUtil.waitForElementsPresence(productPriceData, AppConstants.DEFAULT_TIME);
		String priceValue=prodPriceList.get(0).getText();
		String estTax =  prodPriceList.get(1).getText().split(":")[1].trim();
		productMap.put("Price", priceValue);
		productMap.put("Tax", estTax);

	}
	
	@Step(" verify add to cart")
	public boolean addQtyAndAddToCart(String qty) {
		eleUtil.doSendKeys(qtyAmount, qty);
		eleUtil.doClick(addToCart);
		boolean flag = eleUtil.waitForElementVisible(successMessage,AppConstants.MAX_TIMEOUT).isDisplayed();
		return flag;
	}
	
	@Step("view the cart for products added")
	public CheckOutPage viewCart() {
		eleUtil.doClick(viewCart);
		eleUtil.doClick(viewCartButton);
		return new CheckOutPage(driver);
	}
}
