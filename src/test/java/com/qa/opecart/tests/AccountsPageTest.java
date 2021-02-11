package com.qa.opecart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accountsPageSetUp(){
		accountPage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accountsPageTitleTest()
	{
		String title =accountPage.getAccountsPageTitle();
		System.out.println("Account Page title is : "+ title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void verifyAccountsPageHeaderTest(){
		String header =accountPage.getHeaderValue();
		System.out.println("Account page header is : "+ header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
	}
	
	@Test(priority = 3)
	public void verifyAccountsPageSectionsCountTest(){
		Assert.assertTrue(accountPage.getAccountSectionsCount() == Constants.ACCOUNT_PAGE_SECTION_LIST);
	}
	
	@Test(priority = 4)
	public void verifyAccPageSectionList(){
		List<String> accSecList = accountPage.getAccountSectionsList();
		System.out.println(accSecList);
	}
	
	@Test(priority = 5)
	public void searchTest_iMac(){
		Assert.assertTrue(accountPage.doSearch("iMac"));
		
	}
	
	@Test(priority = 6)
	public void searchTest_Macbook(){
		Assert.assertTrue(accountPage.doSearch("MacBook Air"));
	}
	
	@Test(priority = 7)
	public void productResultTest(){
		accountPage.doSearch("iMac");
		accountPage.selectProductsFromResults("iMac");
	}
}
