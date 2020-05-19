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
		
		System.out.println("SearchResultPageObject - goToPage(pageNumber): " + pNumber);
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-300)");
		
		System.out.println("SearchResultPageObject - goToPage(pageNumber): Scrolled to the bottom.");
		
		WebDriverWait waitForElement = new WebDriverWait(driver, 10);
		waitForElement.until(ExpectedConditions.presenceOfElementLocated(By.className("next-input")));
		
		System.out.println("SearchResultPageObject - goToPage(pageNumber): Wait called.");
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[3]/div/div[1]/div/div/button[2]")).click();
		
		return this;
	}
	
	public ProductPageObject openProductNumber(int productNumber) {
		System.out.println("SearchResultPageObject - openProductNumber(productNumber)");
		
		String prdNumber = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/ul/li[2]/div/div[2]/div[1]/div[1]/a"; // + Integer.toString(productNumber);
		By prodTile = By.xpath(prdNumber);
		
		WebDriverWait waitForElement = new WebDriverWait(driver, 5);
		waitForElement.until(ExpectedConditions.presenceOfElementLocated(prodTile));
		
		driver.findElement(prodTile).click();
		
		System.out.println("SearchResultPageObject - openProductNumber(productNumber): Product selected.");
		
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
			System.out.println("SearchResultPageObject - newCustomerPopUpAppeared(notification) returned true, closing popUp.");
			newCustomerPopUp.close();
		}
		
		return false;
	}
	
	@Override
	public boolean gotProductNotFound(boolean notification) {
		if(notification == true) {
			System.out.println("SearchResultPageObject - gotProductNotFound(notification) returned true, calling goToPage(Integer.parseInt(pNumber) again.");
			this.goToPage(Integer.parseInt(pNumber));
		}
		
		return false;
	}
	
//	private By pageNumberButton(String number) {
//		String locator = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[3]/div/div[1]/div/div/button[PAGENUMBER]";
//		locator = locator.replace("PAGENUMBER", number);
//		return By.xpath(locator);
//	}
}
