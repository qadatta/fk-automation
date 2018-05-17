package com.comitydesigns.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchResultPage extends Page{

	protected WebDriver driver;
	public static By SELECTED_FILTERS = By.id("selectedFilters");
	
	
	public SearchResultPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	

	
	public void verifySearchResultPage(String keyword)
	{
		verifyTextPresentIn(SELECTED_FILTERS,keyword);
	}


	
}
