package com.web.testing.example.glue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	private NewCustomerPopUpObservable popUpObservable;
	private ProductNotFoundObservable productNotFoundObservable;
	
	private HomePageObject homePage;
	private SearchResultPageObject searchResultPage;
	private ProductPageObject productPage;
	
	private String sutUrl;
	
	@Autowired
	public BasicSearchGlue(NewCustomerPopUpObservable popUpObservable,
						   ProductNotFoundObservable productNotFoundObservable,
						   HomePageObject homePage,
						   SearchResultPageObject searchResultPage,
						   ProductPageObject productPage) {
		
		this.popUpObservable = popUpObservable;
		this.productNotFoundObservable = productNotFoundObservable;
		this.homePage = homePage;
		this.searchResultPage = searchResultPage;
		this.productPage = productPage;
	}
	
	@Before
	public void setUp() {
		sutUrl = System.getProperty("sutUrl");
		logger.info("Scenario setUp() - SUT Url defined in env. variable: " + sutUrl);
	}
	
	@Given("I've entered into AliExpress")
	public void i_ve_entered_into_AliExpress() {
		logger.info("=========== Given step ===========");

		homePage.open(sutUrl);
		
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
