package com.web.testing.example.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserFactory implements AbstractFactory<Browser> {
	private static Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
	
	@Override
	public Browser create(String browserType) {
		
		if(browserType.equals("CHROME")) {
			logger.info("Factory method called - create(String browserType): " + browserType);
			
			return new Chrome(); 
		}
		
		return null;
	}

}
