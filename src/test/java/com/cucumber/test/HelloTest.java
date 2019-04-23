package com.cucumber.test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

public class HelloTest {
	
	 @Given("^today is Sunday$")
	    public void today_is_Sunday() {
	        
	    }

	    @When("^I ask whether it's Friday yet$")
	    public void i_ask_whether_is_s_Friday_yet() {
	        
	    }

	    @Then("^I should be told \"([^\"]*)\"$")
	    public void i_should_be_told(String expectedAnswer) {
	        
	    }

}
