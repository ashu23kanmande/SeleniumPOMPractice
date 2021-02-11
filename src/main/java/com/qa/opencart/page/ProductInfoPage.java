package com.qa.opencart.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage extends BaseTest {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productNameHeader = By.cssSelector("#content h1");
	private By productMetaData = By.cssSelector("#content .list-unstyled:nth-of-type(1) li");
	private By productPrice = By.cssSelector("#content .list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	private By prodcutImages = By.cssSelector(".thumbnails img");
	
	public ProductInfoPage(WebDriver driver){
		this.driver =driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public Map<String, String> getProductInformation()
	{
		Map<String,String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name",elementUtil.doGetText(productNameHeader).trim() );
		
		List<WebElement> prodMetaDataList = elementUtil.getElements(productMetaData);
		for(WebElement e : prodMetaDataList){
			//Brand:Apple
			String meta[] = e.getText().split(":");
			String metaName = meta[0].trim();
			String mataValue = meta[1].trim();
			productInfoMap.put(metaName, mataValue);
		}
		
		//pricing
		List<WebElement> productPriceList = elementUtil.getElements(productPrice);
		productInfoMap.put("Price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());
		
		
		return productInfoMap;
	}
	
	public void selectQuantity(String qty){
		elementUtil.doSendKeys(quantity, qty);
	}
	
	public void addToCart(){
		elementUtil.doClick(addToCartButton);
	}
	
	public int getProductImages(){
		int imgCount = elementUtil.getElements(prodcutImages).size();
		System.out.println("total product images: " + imgCount);
		
		return imgCount;
	}
	
	public String getProductInfoPageTitle(String productName){
		String title = elementUtil.waitForPageTitlePresent(productName, 5);
		System.out.println("ProductPageTitle is:" + title);
		return title;
	}
	
	/*public void goToProductInfoPage(String productName) {
		// String productName = "iMac";
		accountPage.doSearch(productName);
		accountPage.selectProductsFromResults(productName);
	}*/
	
}
