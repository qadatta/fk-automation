package com.comitydesigns.automation.common;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest  {

	private WebDriver driver;
	private DesiredCapabilities dc; 
	private String browser = "firefox";// System.getProperty("test.browser");
	private URL hubUrl = null;
	
	public String APP_URL = "http://www.flipkart.com/";
	
	static int TEST_COUNT = 0;

	private static ThreadLocal<WebDriver> driverForThread = new ThreadLocal<WebDriver>() ;	

	
	@BeforeMethod(alwaysRun=true)
	public void setUp() throws Exception
	{
		setDriver();


		driver.get(APP_URL);
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
		}

	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		System.out.println("TEAR DOWN --- "+driverForThread.get() );
		driverForThread.get().quit();

	}
	
	public void setDriver()
	{
		try {
			hubUrl = new URL("http://localhost:4444/wd/hub");
		} catch (MalformedURLException e) {
			
		}
		
		if(null==browser)
			browser = "firefox";
		System.out.println("launching browser- "+browser);
		
		if(browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("FF") )
		{
			dc = DesiredCapabilities.firefox();
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			dc = DesiredCapabilities.chrome();
				
		}
		else if(browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("Internet Explorer") )
		{
			dc = DesiredCapabilities.internetExplorer();

		}
		
		try {
			driver = new RemoteWebDriver(hubUrl, dc);
		} catch (Exception e) {
			System.out.println("problem increating session---------------");
			e.printStackTrace();
		}
		driverForThread.set(driver);
				
	}
	
	
	public static WebDriver getWebDriver()
	{
		return driverForThread.get();
	}
	

	
}