package com.web.testing.example.factory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RemoteChrome implements Browser {
	private static Logger logger = LoggerFactory.getLogger(RemoteChrome.class);
	private WebDriver driver;
	
	@Bean
	@Profile("REMOTE_CHROME")
	public WebDriver getDefaultVersion() {
		try {
			ChromeOptions options = new ChromeOptions();
			driver = new RemoteWebDriver(new URL(System.getProperty("remote.browser.url")), options);
		
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		}
		
		logger.info("Browser instance called - getDefaultVersion(): " + String.valueOf(driver.getClass()));

		return driver;
	}

}
