package com.qa.opencart.listeners;

public class CMDLines {

	// to clear command prompt console -cls
	//to run allure report
	
	//1. go to project : cd project path
	//2. allure serve allure-results 
	
	
	// to run from command prompt
	//1. go to project : cd project path
	//2. project name tree - gives all files in tree format
	//3. check maven --version
	//4. mvn clean install -Denv="qa"
	
	//to run in command promt with specific runner file
	//mvn clean install -Denv="qa" -Dsurefire.suiteXmlFiles="src/test/resource/testRunner/testng_chrome.xml"
	
	//5.from eclipse, 
	// Run as ->maven build - goal -  clean install -Denv="qa"
	
}
