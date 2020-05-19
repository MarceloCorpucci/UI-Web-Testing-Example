Feature: Basic AliExpress search
	As a Customer 
	we want to see if the second Iphone related ad 
	from the second results page from www.aliexpress.com 
	has at least 1 item to be bought.
	
	Scenario: Unregistered customer looks for an IPhone to verify its stock
		Given I've entered into AliExpress
		When I search "Iphone"
		And select the second article from the second page
		Then I should see at least 1 item available to be purchased