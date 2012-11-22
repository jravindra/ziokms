package com.walmartlabs.testdemo.cucumber;

import cucumber.runtime.RuntimeOptions;

/**
 * Responsible for parse the $clazz for any Cucumber.Options annotation and return the
 * configured RuntimeOptions
 */
public interface RuntimeOptionsFactory {

    /**
     * parse and return RuntimeOptions base on Cucumber.Options annotation on the clazz
     *
     * @param clazz the cucumber annotate class
     * @return RuntimeOptions
     */
    RuntimeOptions create(Class clazz);
}
