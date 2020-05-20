package com.web.testing.example.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements Browser {
	private static Logger logger = LoggerFactory.getLogger(Chrome.class);
	private WebDriver driver;
	
	@Override
	public WebDriver getDefaultVersion() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		logger.info("Browser instance called - getDefaultVersion(): " + String.valueOf(driver.getClass()));

		return driver;
	}

}
