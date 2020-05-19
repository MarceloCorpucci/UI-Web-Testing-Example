package com.web.testing.example.pageobject.observer;

import org.openqa.selenium.WebDriver;

import com.web.testing.example.pageobject.section.NewCustomerPopUp;

public class NewCustomerPopUpObservable {
	private ObserverPage currentObserver;
	private WebDriver driver;
	private NewCustomerPopUp popUp;
	
	public NewCustomerPopUpObservable setObserver(ObserverPage observer) {
    	this.driver = observer.getBrowserInstance();
    	this.currentObserver = observer;

    	System.out.println("NewCustomer - setObserver(): " + String.valueOf(observer.getClass()));
      
    	return this;
	}
    
    public boolean popUpNotification() {
    	this.popUp = new NewCustomerPopUp(driver);
    	return this.popUp.getPopUpState();
    }
    
    public void update() {
		System.out.println("NewCustomer - update() - popUpNotification(): " + String.valueOf(popUpNotification()));
		currentObserver.newCustomerPopUpAppeared(popUpNotification());
    }
}
