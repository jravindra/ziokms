package com.walmartlabs.testdemo.cucumber;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class PostMessageDog {
	private String status;

	@When("^I post dog payload from file (.*) to (.*)$")
	public void iPostDogPayloadFromFile(String file, String url)
			throws ClientProtocolException, IOException {
		System.out.println(file);
		System.out.println(url);

		final FileEntity fileEntity = new FileEntity(new File(file));
		HttpClient client = new DefaultHttpClient();
		final String path = "http://localhost:13000/jaxrs-service" + url;
		HttpPost post = new HttpPost(path);
		post.setEntity(fileEntity);
		final HttpResponse response = client.execute(post);
		status = Integer.toString(response.getStatusLine().getStatusCode());
	}

	@Then("^the post response for dog is (.*)$")
	public void thePostResponseForDogIs(String status) {
		Assert.assertEquals("200", status);
	}
}
