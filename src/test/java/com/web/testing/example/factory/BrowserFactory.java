package com.web.testing.example.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserFactory implements AbstractFactory<Browser> {
	private static Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
	
	@Override
	public Browser create(String browserType) {
		logger.info("Factory method called - create(String browserType): " + browserType);
		
		if(browserType.equals("CHROME")) {
			return new Chrome(); 
		}
		
		if(browserType.equals("REMOTE_CHROME")) {
			return new RemoteChrome(); 
		}
		
		
		return null;
	}

}
