package com.web.testing.example.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Chrome implements Browser {
	private static Logger logger = LoggerFactory.getLogger(Chrome.class);
	private WebDriver driver;
	
	@Bean
	@Profile("CHROME")
	public WebDriver getDefaultVersion() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		logger.info("Browser instance called - getDefaultVersion(): " + String.valueOf(driver.getClass()));

		return driver;
	}

}
