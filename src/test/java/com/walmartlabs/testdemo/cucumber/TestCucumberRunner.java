package com.walmartlabs.testdemo.cucumber;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.walmartlabs.testdemo.cucumber.CucumberTestNGRunner;

import cucumber.junit.Cucumber;

/**
 * Cart Cucumber TestNG Runner
 */
@Cucumber.Options(format = { "pretty" })
public class TestCucumberRunner extends CucumberTestNGRunner {
	private static final Logger logger = LoggerFactory
			.getLogger(TestCucumberRunner.class);

	@Override
	public Class getCucumberClass() {
		return this.getClass();
	}

	@Test
	public void shouldRun() {
		try {
			super.run();
		} catch (Exception e) {
			logger.error("failed to Cart features", e);
		}
	}
}
