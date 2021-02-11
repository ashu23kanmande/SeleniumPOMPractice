package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	//by locator
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.cssSelector("input[value = 'Login']");
	private By ForgotPwdLink = By.xpath("(//a[text()='Forgotten Password'])[1]");
	private By registerLink = By.linkText("Register");

	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	//Page Actions
	public String getLoginPageTitle() {
		return elementUtil.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 5);
	}

	public boolean isForgotPasswordLinkExists() throws InterruptedException {
		Thread.sleep(2000);
		return elementUtil.doIsDisplayed(ForgotPwdLink);
	}

	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Login with :" + un + " " + pwd);

		elementUtil.doActionsSendKeys(username, un);
		elementUtil.doActionsSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
	}

	public RegisterPage navigateToRegisterationPage(){
		elementUtil.doClick(registerLink);
		System.out.println("navigate to resgistration page");
		
		return new RegisterPage(driver);
	}
}
