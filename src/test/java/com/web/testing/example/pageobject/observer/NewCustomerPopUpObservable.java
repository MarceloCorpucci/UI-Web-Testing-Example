package com.web.testing.example.pageobject.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.testing.example.pageobject.section.NewCustomerPopUp;

@Component
public class NewCustomerPopUpObservable {
	private static Logger logger = LoggerFactory.getLogger(NewCustomerPopUpObservable.class);
	
	private ObserverPage currentObserver;
//	private WebDriver driver;
	private NewCustomerPopUp popUp;
	
	@Autowired
	public void setNewCustomerPopUp(NewCustomerPopUp popUp) {
		this.popUp = popUp;
	}
	
	public NewCustomerPopUpObservable setObserver(ObserverPage observer) {
//    	this.driver = observer.getBrowserInstance();
    	this.currentObserver = observer;

    	logger.info("Observable - setObserver(): " + String.valueOf(observer.getClass()));
      
    	return this;
	}
    
    public boolean popUpNotification() {
//    	this.popUp = new NewCustomerPopUp(driver);
    	return this.popUp.getPopUpState();
    }
    
    public void update() {
    	logger.info("Observable - update() - popUpNotification(): " + String.valueOf(popUpNotification()));
		currentObserver.newCustomerPopUpAppeared(popUpNotification());
    }
}
