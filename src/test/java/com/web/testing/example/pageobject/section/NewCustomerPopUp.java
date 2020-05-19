package com.web.testing.example.pageobject.section;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewCustomerPopUp {
	private WebDriver driver;
	private By closeButton = By.className("next-dialog-close");
	
	public NewCustomerPopUp(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean getPopUpState() {
		try {
			WebDriverWait waitForAvailability = new WebDriverWait(driver, 5);
			return waitForAvailability.until(ExpectedConditions.elementToBeClickable(closeButton)).isDisplayed();
			
		} catch(TimeoutException e) {
			return false;
		}
	}
	
	public void close() {
		driver.findElement(closeButton).click();
	}

}
