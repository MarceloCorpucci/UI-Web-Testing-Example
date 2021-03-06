package com.web.testing.example.glue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.web.testing.example.factory.BrowserFactory;
import com.web.testing.example.pageobject.HomePageObject;
import com.web.testing.example.pageobject.ProductPageObject;
import com.web.testing.example.pageobject.SearchResultPageObject;
import com.web.testing.example.pageobject.observer.NewCustomerPopUpObservable;
import com.web.testing.example.pageobject.observer.ProductNotFoundObservable;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BasicSearchGlue {
	private static Logger logger = LoggerFactory.getLogger(BasicSearchGlue.class);
	
	private String basedOnExternalParam;
	
	private BrowserFactory browserFactory;
	
	private NewCustomerPopUpObservable popUpObservable;
	private ProductNotFoundObservable productNotFoundObservable;
	
	private HomePageObject homePage;
	private SearchResultPageObject searchResultPage;
	private ProductPageObject productPage;
	
	private String sutUrl;
	
	@Before
	public void setUp() {
		basedOnExternalParam = System.getProperty("browser");
		logger.info("Scenario setUp() - Browser defined in env. variable: " + basedOnExternalParam);

		sutUrl = System.getProperty("sutUrl");
		logger.info("Scenario setUp() - SUT Url defined in env. variable: " + sutUrl);
		
		browserFactory = new BrowserFactory();
		homePage = new HomePageObject(browserFactory
										.create(basedOnExternalParam)
										.getDefaultVersion());
	}
	
	@Given("I've entered into AliExpress")
	public void i_ve_entered_into_AliExpress() {
		logger.info("=========== Given step ===========");

		homePage.open(sutUrl);
		
		popUpObservable = new NewCustomerPopUpObservable();
		popUpObservable
			.setObserver(homePage)
			.update();
	}

	@When("I search {string}")
	public void i_search(String entry) {
		logger.info("=========== When step ===========");
		
		searchResultPage = homePage.search(entry);
		
		popUpObservable
			.setObserver(searchResultPage)
			.update();
		
		productNotFoundObservable = new ProductNotFoundObservable();
		productNotFoundObservable
			.setObserver(homePage)
			.update();
	}

	@When("select the second article from the second page")
	public void select_the_second_article_from_the_second_page() {
		logger.info("=========== And step ===========");
		
		searchResultPage.goToPage(2);
		
		popUpObservable
			.setObserver(searchResultPage)
			.update();
		
		productNotFoundObservable
			.setObserver(searchResultPage)
			.update();
		
		productPage = searchResultPage.openProductNumber(2);
		
		popUpObservable
			.setObserver(productPage)
			.update();
	}

	@Then("I should see at least {int} item available to be purchased")
	public void i_should_see_at_least_item_available_to_be_purchased(Integer expectedAmount) {
		logger.info("=========== Then step ===========");
		
		productPage.changeTab();
		
		popUpObservable
			.setObserver(productPage)
			.update();
		
		int productQty = productPage.getAvailability();
		logger.info("Asserting productQty: " + Integer.toString(productQty) + " | Expected Amount: " + expectedAmount);
		
		assertThat(productQty, equalTo(expectedAmount));
	}
	
	@After
	public void tearDown() {
		logger.info("Scenario tearDown() - killing browser instance.");
		productPage.getBrowserInstance().quit();
	}
}
