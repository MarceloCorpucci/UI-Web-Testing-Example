package com.web.testing.example.pageobject.observer;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.web.testing.example.pageobject.section.NewCustomerPopUp;

public class NewCustomerPopUpObservable {
	private List<ObserverPage> observers = new ArrayList<ObserverPage>();
	private WebDriver driver;
	private NewCustomerPopUp popUp;
	
    public NewCustomerPopUpObservable addObserver(ObserverPage observer) {
    	this.driver = observer.getBrowserInstance();
        this.observers.add(observer);
        return this;
    }
    
    public boolean popUpNotification() {
    	this.popUp = new NewCustomerPopUp(driver);
    	return this.popUp.getPopUpState();
    }
    
    public void update() {
    	for(ObserverPage observer : observers) {
    		observer.newCustomerPopUpAppeared(popUpNotification());
    	}
    }
}
