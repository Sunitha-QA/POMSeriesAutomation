package com.qa.opencart.constants;

public class AppConstants {

	public final static String LOGIN_PAGE_TITLE="Account Login";
	public final static String LOGIN_PAGE_URL_FRACTION="route=account/login";
	public final static String HOME_PAGE_TITLE = "My Account";
	public final static String CHECKOUT_PAGE_TITLE = "Shopping Cart";
	public final static String HOME_PAGE_URL_FRACTION="route=account/account";
	
	public final static String CONFIG_PROD_PROP_FILE_PATH="./src/test/resources/config/config.properties";
	public final static String CONFIG_QA_PROP_FILE_PATH="./src/test/resources/config/qa.config.properties";
	public final static String CONFIG_DEV_PROP_FILE_PATH="./src/test/resources/config/dev.config.properties";
	public final static String CONFIG_UAT_PROP_FILE_PATH="./src/test/resources/config/uat.config.properties";
	public final static String CONFIG_STAGE_PROP_FILE_PATH="./src/test/resources/config/stage.config.properties";

	public final static int DEFAULT_TIME=5;
	public final static int MIN_TIMEOUT=10;
	public final static int MAX_TIMEOUT=20;
	
	public final static String PRODUCT_TEST_DATA_SHEET_NAME="product";

}
