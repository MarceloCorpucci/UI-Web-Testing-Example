package com.web.testing.example.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements Browser {
	
	@Override
	public WebDriver getDefaultVersion() {
		return new ChromeDriver();
	}

}
