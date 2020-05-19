package com.web.testing.example.glue;

import com.web.testing.example.factory.BrowserFactory;
import com.web.testing.example.pageobject.HomePageObject;
import com.web.testing.example.pageobject.SearchResultPageObject;
import com.web.testing.example.pageobject.observer.NewCustomerPopUpObservable;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BasicSearchGlue {
	private String basedOnExternalParam;
	private BrowserFactory browserFactory;
	private NewCustomerPopUpObservable popUpObservable;
	private HomePageObject homePage;
	private SearchResultPageObject searchResultPage;
	private String sutUrl;
	
	@Before
	public void setUp() {
		System.setProperty("browser", "CHROME");
		System.setProperty("webdriver.chrome.driver", "/Users/marcelocorpucci/Chromedriver/chromedriver");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("sutUrl", "https://www.aliexpress.com/");
		
		basedOnExternalParam = String.valueOf(System.getProperty("browser"));
		sutUrl = String.valueOf(System.getProperty("sutUrl"));
		
		browserFactory = new BrowserFactory();
		homePage = new HomePageObject(browserFactory
										.create(basedOnExternalParam)
										.getDefaultVersion());
		
	}
	
	@Given("I've entered into AliExpress")
	public void i_ve_entered_into_AliExpress() {
		homePage.open(sutUrl);
		
		//mediator required in here.
		popUpObservable = new NewCustomerPopUpObservable();
		popUpObservable
			.addObserver(homePage)
			.update();
	}

	@When("I search {string}")
	public void i_search(String entry) {
		searchResultPage = homePage.search(entry);
		
		popUpObservable
			.addObserver(searchResultPage)
			.update();
	}

	@When("select the second article from the second page")
	public void select_the_second_article_from_the_second_page() {
//	     Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I should see at least {int} item available to be purchased")
	public void i_should_see_at_least_item_available_to_be_purchased(Integer int1) {
//	     Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
}
