package com.qa.opecart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.page.AccountsPage;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 0)
	public void loginPageTitleTest(){
		String title =loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is : " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 1)
	public void forgotPasswordLink() throws InterruptedException{
		Assert.assertTrue(loginPage.isForgotPasswordLinkExists());
	}

	@Test(priority = 2)
	public void doLoginTest()
	{
		AccountsPage accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountPage.getAccountsPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}
}
