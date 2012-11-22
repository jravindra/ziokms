package com.walmartlabs.testdemo.cucumber;

import static junit.framework.Assert.assertEquals;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class BasicFeature {
	private String action;
	private String subject;

	@Given("^The Action is ([A-z]*)$")
	public void theActionIs(String action) {
		this.action = action;
	}

	@When("^The Subject is ([A-z]*)$")
	public void theSubjectIs(String subject) {
		this.subject = subject;
	}

	@Then("^The Greeting is ([^\\.]*).$")
	public void theGreetingIs(String greeting) {
		assertEquals(String.format("%s, %s", action, subject), greeting);
	}
}
