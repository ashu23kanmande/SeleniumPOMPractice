package com.qa.opecart.tests;

import java.util.Map;

import org.omg.Messaging.SyncScopeHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void accountsPageSetUp(){
		accountPage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	/*public void goToProductInfoPage(String productName) {
		// String productName = "iMac";
		accountPage.doSearch(productName);
		productInfoPage = accountPage.selectProductsFromResults(productName);
	}*/
	
	@Test
	public void productInfoPageTitleTest_iMac(){
		accountPage.doSearch("iMac");
		productInfoPage =accountPage.selectProductsFromResults("iMac");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"),"iMac");
	}
	
	@Test
	public void productInfoPageTitleTest_Macbook(){
		accountPage.doSearch("MacBook Air");
		productInfoPage =accountPage.selectProductsFromResults("MacBook Air");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("MacBook Air"),"MacBook Air");
	}
	
	@Test
	public void productInfoTest(){
		String productName="iMac";
		/*accountPage.doSearch(productName);
		productInfoPage =accountPage.selectProductsFromResults(productName);*/
		accountPage.goToProductInfoPage(productName);
		Map<String,String> productInfoMap = productInfoPage.getProductInformation();
		
		productInfoMap.forEach((k,v) -> System.out.println("K.." + k +"v.." + v));
	}
}
