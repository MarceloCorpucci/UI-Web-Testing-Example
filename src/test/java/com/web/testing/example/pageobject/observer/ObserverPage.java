package com.web.testing.example.pageobject.observer;

import org.openqa.selenium.WebDriver;

public interface ObserverPage {
	public WebDriver getBrowserInstance();
	public boolean newCustomerPopUpAppeared(boolean notification);
	public boolean gotProductNotFound(boolean notification);
}
