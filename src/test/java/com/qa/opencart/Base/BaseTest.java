package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.Factory.DriverFactory;
import com.qa.opencart.page.AccountsPage;
import com.qa.opencart.page.LoginPage;
import com.qa.opencart.page.ProductInfoPage;
import com.qa.opencart.page.RegisterPage;

public class BaseTest {

	DriverFactory df;
	public Properties prop;
	WebDriver driver;
	public LoginPage loginPage;
	public AccountsPage accountPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;

	@BeforeTest
	public void setup() {

		df = new DriverFactory();
		prop = df.init_Prop();
		//String browserName = prop.getProperty("browser");
		driver = df.init_driver(prop);
		driver.get(prop.getProperty("url"));
		
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void TearDown() {
		
		//driver.quit();
	}
}
