package com.web.testing.example.pageobject.observer;

import org.openqa.selenium.WebDriver;

public interface ObserverPopUp {
	public WebDriver getBrowserInstance();
	public boolean newCustomerPopUpAppeared(boolean notification);
}
