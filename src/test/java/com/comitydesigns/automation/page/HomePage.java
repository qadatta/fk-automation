package com.comitydesigns.automation.page;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;


public class HomePage extends Page{

	protected WebDriver driver;
	public static String PAGE_TITLE = "Investing.com - Stock Market Quotes & Financial News";
	public static By SEARCH_FIELD = By.id("fk-top-search-box");
	public static By SEARCH_BTN = By.cssSelector("input.search-bar-submit");
	public static By LOGIN_LINK = By.partialLinkText("Login");
	public static By LOGGED_IN_STATE = By.cssSelector("li.greeting-link>a");
	public static By LOGOUT_LINK = By.partialLinkText("Logout");

	
	
	public static By SELECTED_FILTERS = By.id("selectedFilters");

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	

	
	public void searchKeyword(String keyword)
	{
		waitForElementVisible(SEARCH_FIELD);
		Reporter.log("Type keyword in search field-"+keyword);
		clearAndType(SEARCH_FIELD, keyword);
		Reporter.log("Click on Search Button");
		click(SEARCH_BTN);
		waitForPageLoad();
	}


	public void verifySearchResultPage(String keyword)
	{
		verifyTextPresentIn(SELECTED_FILTERS,keyword.toUpperCase());
		Reporter.log("Verified search term in upper case on search result page.");
	}

	public void retriveKeywordsAndThenSearchOnHomePage() {
		
		ArrayList<String> keywordList = retriveKeywordsToSearch();
		
		for (Iterator iterator = keywordList.iterator(); iterator.hasNext();) {
			String keyword = (String) iterator.next();
			
			
			searchKeyword(keyword);
			
			verifySearchResultPage(keyword);
			
		}
		
	}

	public HomePage verifyUserLoggedInState()
	{
		waitForElementVisible(LOGGED_IN_STATE);
		assertElementPresent(LOGGED_IN_STATE);
		return new HomePage(driver);
	}

	public LoginModal clickOnLoginLink() {
		waitForElementVisible(LOGIN_LINK);
		Reporter.log("Click on Login Link present in Header");
		click(LOGIN_LINK);	
		return new LoginModal(driver);
	}
	
	public HomePage clickOnLogOutLink() {
		waitForElementVisible(LOGGED_IN_STATE);
		Reporter.log("Click on Logout Link present iin header");
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(LOGGED_IN_STATE)).build().perform();
		
		click(LOGOUT_LINK);	
		return new HomePage(driver);
	}
	
	public void verifyUserIsLogOutFromApplication()
	{
		waitForElementVisible(LOGIN_LINK);
		assertElementPresent(LOGIN_LINK);
		Reporter.log("User is logged out from application");
	}
	
	public ArrayList<String> readColumnFromCSVFile(String fileName,String header) {
	    
		ArrayList<String> keyWords = new ArrayList<String>();
		String splitBy = ",";
		BufferedReader br = null;
		try {
		br = new BufferedReader(new FileReader("src/test/java/com/comitydesigns/automation/testdata/"+fileName));
		String line;
		int columnIndex = 0;
		line = br.readLine();
		boolean headerRecord = true;
		while(line!=null){
		    String[] reocrd = line.split(splitBy);
		    
		    if(headerRecord)
		    {
		    	for (int i = 0; i < reocrd.length; i++) {
		    
				if(header.equals(reocrd[i]))
					columnIndex = i;
		    	}
		    	headerRecord = false;
		    }	
		    else
		    	keyWords.add(reocrd[columnIndex]);
		    line = br.readLine();
		}
		br.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		 
		return keyWords;
		}

		public ArrayList<String> retriveKeywordsToSearch()
		{
		ArrayList<String> keywordList = readColumnFromCSVFile("keywords.csv","SearchKeywords");

		return keywordList;
		}
	
}
