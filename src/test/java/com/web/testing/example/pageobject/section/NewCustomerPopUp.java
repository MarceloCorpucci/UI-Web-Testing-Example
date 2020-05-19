package com.web.testing.example.pageobject.section;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class NewCustomerPopUp {
	private WebDriver driver;
	private By closeButton = By.className("next-dialog-close");
	
	public NewCustomerPopUp(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean getPopUpState() {
		Wait<WebDriver> wait;
		try {
			wait = new FluentWait<WebDriver>(driver)
		                .withTimeout(Duration.ofSeconds(10))
		                .pollingEvery(Duration.ofSeconds(1))
		                .ignoring(NoSuchElementException.class);
			
			return wait.until(ExpectedConditions.presenceOfElementLocated(closeButton)).isDisplayed();
			
		} catch(TimeoutException e) {
			return false;
		}
		
	}
	
	public void close() {
		driver.findElement(closeButton).click();
	}
	
	public WebDriver getBrowserInstance() {
		return this.driver;
	}

}
