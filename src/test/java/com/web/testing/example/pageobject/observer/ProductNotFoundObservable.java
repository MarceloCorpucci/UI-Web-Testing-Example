package com.web.testing.example.pageobject.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.testing.example.pageobject.section.ProductNotFoundSection;

@Component
public class ProductNotFoundObservable {
	private static Logger logger = LoggerFactory.getLogger(ProductNotFoundObservable.class);
	
	private ObserverPage currentObserver;
//	private WebDriver driver;
	private ProductNotFoundSection productNotFound;
	
	@Autowired
	public void setProductNotFoundSection(ProductNotFoundSection productNotFound) {
		this.productNotFound = productNotFound;
	}
	
	public ProductNotFoundObservable setObserver(ObserverPage observer) {
//    	this.driver = observer.getBrowserInstance();
    	this.currentObserver = observer;
      
    	logger.info("Observable - setObserver(): " + String.valueOf(observer.getClass()));
      
    	return this;	
	}

    public boolean productNotFoundNotification() {
//    	this.productNotFound = new ProductNotFoundSection(driver);
    	return this.productNotFound.getErrorState();
    }
    
    public void update() {
    	logger.info("Observable - update() - productNotFoundNotification(): " + String.valueOf(productNotFoundNotification()));
		currentObserver.gotProductNotFound(productNotFoundNotification());
    }	
}
