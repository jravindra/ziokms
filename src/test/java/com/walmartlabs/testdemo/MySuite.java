package com.walmartlabs.testdemo;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({ MyTest.class, HelloWorldTest.class })
@IncludeCategory(UnitTests.class)
@ExcludeCategory(DemoTests.class)
public class MySuite {

}
