package com.comitydesigns.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class LoginModal extends Page{

	protected WebDriver driver;
	public static By LOGIN_EMAIL_TF = By.cssSelector("input.user-email"	);
	public static By LOGIN_PWD_TF = By.cssSelector("input.user-pwd");
	public static By LOG_IN_LINK = By.cssSelector("input.login-btn");
	
	
	public LoginModal(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public LoginModal inputUserEmailAndPasswordForLogin(String email,String pwd)
	{
		enterLoginEmail(email);
		enterLoginPassword(pwd);
		return new LoginModal(driver);
	}
	
	public void enterLoginEmail(String email)
	{
		Reporter.log("Enter email - "+email +" for login to application");
		waitForElementVisible(LOGIN_EMAIL_TF);
		clearAndType(LOGIN_EMAIL_TF, email);

	}
	
	public void enterLoginPassword(String pwd)
	{
		Reporter.log("Enter password - "+pwd +" for login to application");

		waitForElementVisible(LOGIN_PWD_TF);
		
		clearAndType(LOGIN_PWD_TF, pwd);
	}
	
	public void clickOnLoginInButton()
	{
		Reporter.log("Click on Log in button on modal");

		waitForElementVisible(LOG_IN_LINK);
		click(LOG_IN_LINK);
	}
	
	public HomePage loginToApplication(String email,String pwd)
	{
		enterLoginEmail(email);
		enterLoginPassword(pwd);
		clickOnLoginInButton();
		return new HomePage(driver);
	}
	
	
}
