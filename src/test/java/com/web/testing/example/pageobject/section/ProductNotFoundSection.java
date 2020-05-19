package com.web.testing.example.pageobject.section;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class ProductNotFoundSection {
	private WebDriver driver;
	private By errrorIcon = By.className("next-icon-error");
	
	public ProductNotFoundSection(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean getErrorState() {
		Wait<WebDriver> wait;
		try {
			wait = new FluentWait<WebDriver>(driver)
		                .withTimeout(Duration.ofSeconds(5))
		                .pollingEvery(Duration.ofSeconds(1))
		                .ignoring(NoSuchElementException.class);
			
			return wait.until(ExpectedConditions.presenceOfElementLocated(errrorIcon)).isDisplayed();
			
		} catch(TimeoutException e) {
			return false;
		}
		
	}

}
