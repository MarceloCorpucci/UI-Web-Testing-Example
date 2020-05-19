package com.web.testing.example.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements Browser {
	private WebDriver driver;
	
	@Override
	public WebDriver getDefaultVersion() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

}
