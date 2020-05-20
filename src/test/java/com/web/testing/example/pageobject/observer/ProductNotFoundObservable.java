package com.web.testing.example.pageobject.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.WebDriver;

import com.web.testing.example.pageobject.section.ProductNotFoundSection;

public class ProductNotFoundObservable {
	private static Logger logger = LoggerFactory.getLogger(ProductNotFoundObservable.class);
	
	private ObserverPage currentObserver;
	private WebDriver driver;
	private ProductNotFoundSection productNotFound;
	
	public ProductNotFoundObservable setObserver(ObserverPage observer) {
    	this.driver = observer.getBrowserInstance();
    	this.currentObserver = observer;
      
    	logger.info("Observable - setObserver(): " + String.valueOf(observer.getClass()));
      
    	return this;	
	}

    public boolean productNotFoundNotification() {
    	this.productNotFound = new ProductNotFoundSection(driver);
    	return this.productNotFound.getErrorState();
    }
    
    public void update() {
    	logger.info("Observable - update() - productNotFoundNotification(): " + String.valueOf(productNotFoundNotification()));
		currentObserver.gotProductNotFound(productNotFoundNotification());
    }	
}
