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

public class MrNotifyFeature {
	private String status;

	@When("^I post manual notify payload from file (.*) to (.*)$")
	public void iPostManualNotifyPayload(String file, String url)
			throws ClientProtocolException, IOException {
		System.out.println(file);
		System.out.println(url);

		final FileEntity fileEntity = new FileEntity(new File(file));

		HttpClient client = new DefaultHttpClient();
		final String path = "http://localhost:65000/fraudservices/api" + url;
		HttpPost post = new HttpPost(path);
		post.setEntity(fileEntity);
		addMandatoryHeaders(post);
		final HttpResponse response = client.execute(post);
		status = Integer.toString(response.getStatusLine().getStatusCode());
	}

	@Then("^the response from manual notify is (.*)$")
	public void thePostResponseForDogIs(String status) {
		Assert.assertEquals("200", status);
	}

	private void addMandatoryHeaders(HttpPost httppost) {
		httppost.addHeader("WM_CONSUMER.ID", "100");
		httppost.addHeader("WM_SEC.AUTH_TOKEN", "ahha%&!^!)(!&");
		httppost.addHeader("WM_QOS.CORRELATION_ID", "12312314151515");
		httppost.addHeader("WM_SVC.VERSION", "1.0");
		httppost.addHeader("WM_SVC.ENV", "DEV");
		httppost.addHeader("WM_IFX.CLIENT_TYPE", "NON_JAVA");
		httppost.addHeader("WM_CONSUMER.INTIMESTAMP", "11221122");
		httppost.addHeader("WM_CONSUMER.IP", "10.10.10.10");
		httppost.addHeader("WM_Plugin.Version", "1.0");
	}
}
