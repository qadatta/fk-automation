package com.comitydesigns.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.comitydesigns.automation.common.BaseTest;


public class Page extends BaseTest{

	protected WebDriver driver  = getWebDriver();
	//public static WebDriver driver = getWebDriver();
	
	public Page(WebDriver driver)
	{
		this.driver = driver;
	}
	


	public  WebElement waitForElementVisible(By locator) 
	{
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 90);
		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
		}
		return element;
	}
	
	public void assertElementPresent(By locator)
	{
		WebElement weblement = waitForElementVisible(locator);
		
		if (null== weblement) {
			Assert.fail("Element is not present");
		}
		
	}
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public void click(By locator)
	{
		try {
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (Exception e) {
			System.out.println("Element is not clicable");
		}
		
	}
	
	public void clearAndType(By locator , String str)
	{
		WebElement element = driver.findElement(locator);
		if(element!=null)
		{
			element.clear();
			element.sendKeys(str);
		}
		else
			Assert.fail("Element is not available to type");
			
		
	}

	public boolean verifyTextPresentIn(By locator,String textTosearch)
	{
		try {
			WebElement element = driver.findElement(locator);
			String text = element.getText();
			Assert.assertTrue(text.contains(textTosearch), textTosearch + " is not present");
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public void waitForPageLoad() {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    wait.until(pageLoadCondition);
	}
	
	
	
	
}
