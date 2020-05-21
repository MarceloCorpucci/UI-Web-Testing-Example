package com.web.testing.example.pageobject.section;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewCustomerPopUp {
	private WebDriver driver;
	private By closeButton = By.className("next-dialog-close");
	
	@Autowired
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
