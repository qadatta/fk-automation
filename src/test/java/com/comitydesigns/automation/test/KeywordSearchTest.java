package com.comitydesigns.automation.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.comitydesigns.automation.common.BaseTest;
import com.comitydesigns.automation.common.SiteFactory;

public class KeywordSearchTest extends BaseTest{

	@Test(groups={"SmokeTest"})
	public void keywordSearchTest() throws InterruptedException {
		WebDriver driver = getWebDriver();
		SiteFactory siteFactory = new SiteFactory(driver);

		
		
		siteFactory
		.homePage()
		.clickOnLoginLink()
		.loginToApplication("datta.more@outlook.com", "p@ssw0rd")
		.verifyUserLoggedInState()
		.retriveKeywordsAndThenSearchOnHomePage();
		
		siteFactory
		.homePage()
		.clickOnLogOutLink()
		.verifyUserIsLogOutFromApplication();
		
	}


	
	
	

	
}