package com.qa.opecart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void accountsPageSetUp(){
		registerPage =loginPage.navigateToRegisterationPage();
	}

	@DataProvider
	public Object[][] getResgisterData(){
		Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider="getResgisterData")
	public void userRegisterationTest(String firstName, String lastName, String emailId, String phone, String password, String subscribe){
		
		Assert.assertTrue(registerPage.accountRegistration(firstName,lastName,emailId, phone, password,subscribe ));
	}
}
