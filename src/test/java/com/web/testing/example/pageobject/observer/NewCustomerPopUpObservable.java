package com.web.testing.example.pageobject.observer;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.web.testing.example.pageobject.section.NewCustomerPopUp;

public class NewCustomerPopUpObservable {
	private List<ObserverPopUp> observers = new ArrayList<ObserverPopUp>();
	private WebDriver driver;
	private NewCustomerPopUp popUp;
	
	public NewCustomerPopUpObservable(WebDriver driver) {
		this.driver = driver;
	}
	
    public NewCustomerPopUpObservable addObserver(ObserverPopUp observer) {
        this.observers.add(observer);
        return this;
    }
    
    public boolean popUpNotification() {
    	this.popUp = new NewCustomerPopUp(driver);
    	return this.popUp.getPopUpState();
    }
    
    public void update() {
    	for(ObserverPopUp observer : observers) {
    		observer.newCustomerPopUpAppeared(popUpNotification());
    	}
    }
}
