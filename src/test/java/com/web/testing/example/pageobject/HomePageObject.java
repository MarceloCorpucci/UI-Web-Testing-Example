package com.web.testing.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.testing.example.pageobject.observer.ObserverPage;
import com.web.testing.example.pageobject.section.NewCustomerPopUp;

public class HomePageObject implements ObserverPage {
	private WebDriver driver;
	private NewCustomerPopUp newCustomerPopUp;
	private By searchInput = By.id("search-key");
	private By searchButton = By.id("search-cate");
	private String productName;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePageObject open(String url) {
		driver.get(url);
		
		WebDriverWait waitForAvailability = new WebDriverWait(driver, 10);
		waitForAvailability.until(ExpectedConditions.elementToBeClickable(searchInput));
		
		return this;
	}
	
	public SearchResultPageObject search(String productName) {
		this.productName = productName;
		
		WebElement input = driver.findElement(searchInput);
		input.clear();
		input.sendKeys(productName);
		driver.findElement(searchButton).submit();
		
		WebDriverWait waitForAvailability = new WebDriverWait(driver, 10);
		waitForAvailability.until(ExpectedConditions.urlContains("wholesale"));
		
		return new SearchResultPageObject(driver);
	}
	
	@Override
	public WebDriver getBrowserInstance() {
		return this.driver;
	}

	@Override
	public boolean newCustomerPopUpAppeared(boolean notification) {
		this.newCustomerPopUp = new NewCustomerPopUp(driver);
		
		if(notification == true) {
			newCustomerPopUp.close();
		}
		
		return false;
	}

	@Override
	public boolean gotProductNotFound(boolean notification) {
		if(notification == true) {
			this.search(productName);
		}
		
		return false;
	}
}
