package com.web.testing.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageObject {
	private WebDriver driver;
	private By searchInput = By.id("search-key");
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePageObject open(String url) {
		driver.get(url);
		
		WebDriverWait waitForAvailability = new WebDriverWait(driver, 10);
		waitForAvailability.until(ExpectedConditions.elementToBeClickable(searchInput));
		
		return this;
	}
	
	public SearchListPageObject search(String entry) {
		driver.findElement(searchInput).sendKeys(entry);
		
		WebDriverWait waitForAvailability = new WebDriverWait(driver, 10);
		waitForAvailability.until(ExpectedConditions.urlContains("wholesale"));
		
		return new SearchListPageObject(driver);
	}
}
