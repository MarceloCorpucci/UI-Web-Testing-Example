package com.web.testing.example.pageobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.testing.example.pageobject.observer.ObserverPage;
import com.web.testing.example.pageobject.section.NewCustomerPopUp;

public class SearchResultPageObject implements ObserverPage {
	private static Logger logger = LoggerFactory.getLogger(SearchResultPageObject.class);
	
	private WebDriver driver;
	
	private NewCustomerPopUp newCustomerPopUp;
	
	private String pNumber;
	
	public SearchResultPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public SearchResultPageObject goToPage(int pageNumber) {
		pNumber = Integer.toString(pageNumber);
		
		logger.info("Method called - goToPage(pageNumber): " + pNumber);
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-300)");
		
		logger.info("Method called - goToPage(pageNumber): Scrolled to the bottom.");
		
		WebDriverWait waitForElement = new WebDriverWait(driver, 10);
		waitForElement.until(ExpectedConditions.presenceOfElementLocated(By.className("next-input")));
		
		logger.info("Method called - goToPage(pageNumber): Waiting for page button to be located.");
		
		driver.findElement((pageNumberButton(pNumber))).click();
		
		return this;
	}
	
	public ProductPageObject openProductNumber(int productNumber) {
		String prdNumber = Integer.toString(productNumber);
		
		logger.info("Method called - openProductNumber(productNumber): " + prdNumber);
		
		WebDriverWait waitForElement = new WebDriverWait(driver, 5);
		waitForElement.until(ExpectedConditions.presenceOfElementLocated(prodNumberLocator(prdNumber)));
		
		driver.findElement(prodNumberLocator(prdNumber)).click();
		
		logger.info("Method called - openProductNumber(productNumber): Product selected.");
		
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
			logger.info("Observer notified - newCustomerPopUpAppeared(notification) returned true, closing popUp.");
			newCustomerPopUp.close();
		}
		
		return false;
	}
	
	@Override
	public boolean gotProductNotFound(boolean notification) {
		if(notification == true) {
			logger.info("Observer notified - gotProductNotFound(notification) returned true, calling goToPage(Integer.parseInt(pNumber) again.");
			this.goToPage(Integer.parseInt(pNumber));
		}
		
		return false;
	}
	
	private By pageNumberButton(String number) {
		String locator = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[3]/div/div[1]/div/div/button[PAGENUMBER]";
		locator = locator.replace("PAGENUMBER", number);
		return By.xpath(locator);
	}
	
	private By prodNumberLocator(String number) {
		String prdNumber = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/ul/li[" + number + "]/div/div[2]/div[1]/div[1]/a";
		return By.xpath(prdNumber);
	}
}
