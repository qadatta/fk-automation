
package com.comitydesigns.automation.common;

import org.openqa.selenium.WebDriver;

import com.comitydesigns.automation.page.HomePage;
import com.comitydesigns.automation.page.LoginModal;
import com.comitydesigns.automation.page.Page;
import com.comitydesigns.automation.page.SearchResultPage;


public class SiteFactory extends Page{

	protected WebDriver driver;

	
	public SiteFactory(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public HomePage homePage()
	{
		return new HomePage(driver);
	}
	
	public SearchResultPage searchResultPage()
	{
		return new SearchResultPage(driver);
	}
	public LoginModal loginModal()
	{
		return new LoginModal(driver);
	}

}
