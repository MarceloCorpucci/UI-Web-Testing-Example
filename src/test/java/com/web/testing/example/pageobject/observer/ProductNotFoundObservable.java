package com.web.testing.example.pageobject.observer;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.web.testing.example.pageobject.section.ProductNotFoundSection;

public class ProductNotFoundObservable {
	private List<ObserverPage> observers = new ArrayList<ObserverPage>();
	private WebDriver driver;
	private ProductNotFoundSection productNotFound;
	
	public ProductNotFoundObservable addObserver(ObserverPage observer) {
    	this.driver = observer.getBrowserInstance();
        this.observers.add(observer);
        return this;
    }

    public boolean productNotFoundNotification() {
    	this.productNotFound = new ProductNotFoundSection(driver);
    	return this.productNotFound.getErrorState();
    }
    
    public void update() {
    	for(ObserverPage observer : observers) {
    		observer.gotProductNotFound(productNotFoundNotification());
    	}
    }	
}
