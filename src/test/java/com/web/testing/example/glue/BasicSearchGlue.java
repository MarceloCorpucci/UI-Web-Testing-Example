package com.web.testing.example.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BasicSearchGlue {
	@Given("I've entered into AliExpress")
	public void i_ve_entered_into_AliExpress() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I search {string}")
	public void i_search(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("select the second article from the second page")
	public void select_the_second_article_from_the_second_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I should see at least {int} item available to be purchased")
	public void i_should_see_at_least_item_available_to_be_purchased(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
}
