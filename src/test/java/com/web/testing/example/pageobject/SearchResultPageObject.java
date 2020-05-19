package com.web.testing.example.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.testing.example.pageobject.observer.ObserverPage;
import com.web.testing.example.pageobject.section.NewCustomerPopUp;

public class SearchResultPageObject implements ObserverPage {
	private WebDriver driver;
	private NewCustomerPopUp newCustomerPopUp;
	private String pNumber;
	
	public SearchResultPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public SearchResultPageObject goToPage(int pageNumber) {
		pNumber = Integer.toString(pageNumber);
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		WebDriverWait waitForAvailability = new WebDriverWait(driver, 10);
		waitForAvailability.until(ExpectedConditions.presenceOfElementLocated(pageNumberButton(pNumber)));
		
		driver.findElement(pageNumberButton(Integer.toString(pageNumber))).click();
		
		WebDriverWait waitForUrl = new WebDriverWait(driver, 10);
		waitForUrl.until(ExpectedConditions.urlContains("page=" + pNumber));
		
		return this;
	}
	
	public ProductPageObject openProductNumber(int productNumber) {
		driver.findElement(By.cssSelector("product-index=\"2\"")).click();
		
		return new ProductPageObject(driver);
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
			this.goToPage(Integer.parseInt(pNumber));
		}
		
		return false;
	}
	
	private By pageNumberButton(String number) {
		String locator = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[3]/div/div[1]/div/div/button[PAGENUMBER]";
		locator = locator.replace("PAGENUMBER", number);
		return By.xpath(locator);
	}
}
