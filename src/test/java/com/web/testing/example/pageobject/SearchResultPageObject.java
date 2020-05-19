package com.web.testing.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.testing.example.pageobject.observer.ObserverPopUp;
import com.web.testing.example.pageobject.section.NewCustomerPopUp;

public class SearchResultPageObject implements ObserverPopUp {
	private WebDriver driver;
	private NewCustomerPopUp newCustomerPopUp;
	
	public SearchResultPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public SearchResultPageObject goToPage(int pageNumber) {
		WebDriverWait waitForAvailability = new WebDriverWait(driver, 10);
		waitForAvailability.until(ExpectedConditions.elementToBeClickable(pageNumberButton(Integer.toString(pageNumber))));
		
		driver.findElement(pageNumberButton(Integer.toString(pageNumber))).click();
		
		return this;
	}
	
	private By pageNumberButton(String number) {
		String locator = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[3]/div/div[1]/div/div/button[PAGENUMBER]";
		locator.replace("PAGENUMBER", number);
		return By.xpath(locator);
	}

	@Override
	public boolean newCustomerPopUpAppeared(boolean notification) {
		this.newCustomerPopUp = new NewCustomerPopUp(driver);
		
		if(notification == true) {
			newCustomerPopUp.close();
		}
		
		return false;
	}
}
