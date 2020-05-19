package com.web.testing.example.pageobject.observer;

import org.openqa.selenium.WebDriver;

import com.web.testing.example.pageobject.section.ProductNotFoundSection;

public class ProductNotFoundObservable {
	private ObserverPage currentObserver;
	private WebDriver driver;
	private ProductNotFoundSection productNotFound;
	
	public ProductNotFoundObservable setObserver(ObserverPage observer) {
    	this.driver = observer.getBrowserInstance();
    	this.currentObserver = observer;
      
    	System.out.println("ProductNotFoundObservable - setObserver(): " + String.valueOf(observer.getClass()));
      
    	return this;	
	}

    public boolean productNotFoundNotification() {
    	this.productNotFound = new ProductNotFoundSection(driver);
    	return this.productNotFound.getErrorState();
    }
    
    public void update() {
		System.out.println("ProductNotFoundObservable - update() - productNotFoundNotification(): " + String.valueOf(productNotFoundNotification()));
		currentObserver.gotProductNotFound(productNotFoundNotification());
    }	
}
