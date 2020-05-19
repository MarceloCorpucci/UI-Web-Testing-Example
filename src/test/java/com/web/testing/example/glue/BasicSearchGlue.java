package com.web.testing.example.glue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.web.testing.example.factory.BrowserFactory;
import com.web.testing.example.pageobject.HomePageObject;
import com.web.testing.example.pageobject.ProductPageObject;
import com.web.testing.example.pageobject.SearchResultPageObject;
import com.web.testing.example.pageobject.observer.NewCustomerPopUpObservable;
import com.web.testing.example.pageobject.observer.ProductNotFoundObservable;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BasicSearchGlue {
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
		
		System.out.println("setUp - Got Browser: " + basedOnExternalParam);
		
	}
	
	@Given("I've entered into AliExpress")
	public void i_ve_entered_into_AliExpress() {
		System.out.println("Given step - homePage opened: " + sutUrl);

		homePage.open(sutUrl);
		
		//mediator required in here.
		popUpObservable = new NewCustomerPopUpObservable();
		popUpObservable
			.setObserver(homePage)
			.update();
	}

	@When("I search {string}")
	public void i_search(String entry) {
		System.out.println("When step - entry: " + entry);
		
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
		System.out.println("Then step");
		
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
		popUpObservable
			.setObserver(productPage)
			.update();
		
		assertThat(productPage.getAvailability(), equalTo(expectedAmount));
	}
}
