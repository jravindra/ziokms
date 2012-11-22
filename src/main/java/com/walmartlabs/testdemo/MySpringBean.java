package com.walmartlabs.testdemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class MySpringBean {

	private String name = "Hello";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
