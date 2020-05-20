package com.web.testing.example.pageobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.testing.example.pageobject.observer.ObserverPage;
import com.web.testing.example.pageobject.section.NewCustomerPopUp;

public class ProductPageObject implements ObserverPage {
	private static Logger logger = LoggerFactory.getLogger(ProductPageObject.class);
	
	private WebDriver driver;
	private NewCustomerPopUp newCustomerPopUp;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public ProductPageObject changeTab() {
		logger.info("Method called - getAvailability(): About to switch tab.");
		
		String currentHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for(String actual: handles) {
	         if(!actual.equalsIgnoreCase(currentHandle)) {
	             driver.switchTo().window(actual);
	         }
        }
        
        logger.info("Method called - getAvailability(): switched to product tab, waiting for url.");
		
        WebDriverWait waitForUrl = new WebDriverWait(driver, 10);
		waitForUrl.until(ExpectedConditions.urlContains("item"));
		
		return this;
	}
	
	public int getAvailability() {
		logger.info("Method called - getAvailability(): About to get product Qty. Url " + driver.getCurrentUrl());
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)");
		
		logger.info("Method called - getAvailability(): scroll to product Qty.");
		
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
			logger.info("Observer notified - newCustomerPopUpAppeared(notification) returned true, closing popUp.");
			newCustomerPopUp.close();
		}
		
		return false;
	}

	@Override
	public boolean gotProductNotFound(boolean notification) {
		return false;
	}
}
