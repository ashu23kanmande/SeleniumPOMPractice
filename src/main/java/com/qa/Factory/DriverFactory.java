package com.qa.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author k.ashwini.manohar
 *
 */
public class DriverFactory {

	WebDriver driver;
	private Properties prop;
	public static String highlight;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>(); 

	public WebDriver init_driver(Properties prop) {
		String browser = prop.getProperty("browser");
		System.out.println("browsername is: " + browser);
		
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} 
		else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} 
		else if (browser.equals("safari")) {
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} 
		else {
			System.out.println("Please pass the correct browser: " + browser);
		}

		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();

		return getDriver();
	}
	
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}

	public Properties init_Prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}
	
	/**
	 * Take screenshot
	 */
	public String getScreenshot(){
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}

}
