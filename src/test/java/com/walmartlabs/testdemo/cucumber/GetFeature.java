package com.walmartlabs.testdemo.cucumber;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class GetFeature {
	private String path;
	private String name;

	@When("^the request GET (.*)$")
	public void theRequestGET(String url) throws IllegalStateException, IOException {
		this.path = "http://localhost:13000/jaxrs-service" + url;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(this.path);

		final HttpResponse response = client.execute(get);
		final HttpEntity entity = response.getEntity();
		name = EntityUtils.toString(entity);
	}

	@Then("^the response is (.*)$")
	public void theResponseIs(String name) {
		assertEquals(name, this.name);
	}
}
