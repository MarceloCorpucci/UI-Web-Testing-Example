package com.web.testing.example.pageobject.section;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductNotFoundSection {
	private WebDriver driver;
	private By errrorIcon = By.className("next-icon-error");
	
	public ProductNotFoundSection(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean getErrorState() {
		try {
			WebDriverWait waitForAvailability = new WebDriverWait(driver, 5);
			return waitForAvailability.until(ExpectedConditions.elementToBeClickable(errrorIcon)).isDisplayed();
			
		} catch(TimeoutException e) {
			return false;
		}
		
	}

}
