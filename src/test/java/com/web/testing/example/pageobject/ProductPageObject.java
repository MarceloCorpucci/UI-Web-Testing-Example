package com.web.testing.example.pageobject;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.testing.example.pageobject.observer.ObserverPage;
import com.web.testing.example.pageobject.section.NewCustomerPopUp;

public class ProductPageObject implements ObserverPage {
	private WebDriver driver;
	private NewCustomerPopUp newCustomerPopUp;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public int getAvailability() {
		System.out.println("ProductPageObject - getAvailability(): About to switch tab.");
		
		//extract to another method, call observable then.
		String currentHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for(String actual: handles) {
	         if(!actual.equalsIgnoreCase(currentHandle)) {
	             driver.switchTo().window(actual);
	         }
        }
		
		System.out.println("ProductPageObject - getAvailability(): switched to product tab, waiting for url.");
		
		WebDriverWait waitForUrl = new WebDriverWait(driver, 10);
		waitForUrl.until(ExpectedConditions.urlContains("item"));
		
		System.out.println("ProductPageObject - getAvailability(): wait finished. Url " + driver.getCurrentUrl());
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)");
		
		System.out.println("ProductPageObject - getAvailability(): scroll to product Qty.");
		
		String itemAmount = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div[8]/span/span/span[2]/input";
		return Integer.parseInt(driver.findElement(By.xpath(itemAmount)).getAttribute("aria-valuemin"));
	}

	@Override
	public WebDriver getBrowserInstance() {
		return driver;
	}

	@Override
	public boolean newCustomerPopUpAppeared(boolean notification) {
		this.newCustomerPopUp = new NewCustomerPopUp(driver);
		
		if(notification == true) {
			System.out.println("ProductPageObject - newCustomerPopUpAppeared(notification) returned true, closing popUp.");
			newCustomerPopUp.close();
		}
		
		return false;
	}

	@Override
	public boolean gotProductNotFound(boolean notification) {
		// TODO Auto-generated method stub
		return false;
	}
}
