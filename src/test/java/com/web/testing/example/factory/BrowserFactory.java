package com.web.testing.example.factory;

public class BrowserFactory implements AbstractFactory<Browser> {
	
	@Override
	public Browser create(String browserType) {
		
		if(browserType.equals("CHROME")) {
			return new Chrome(); 
		}
		
		return null;
	}

}
