package com.walmartlabs.testdemo;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:beans.xml"})
@PrepareForTest(MyConstants.class)
@Category({DemoTests.class})
public class MyTest {

	    @Rule
	    public PowerMockRule rule = new PowerMockRule();

	    @Autowired
	    private MySpringBean mySpringBean;
	    
	    @Before
	    public void setUp() {
	    	mockStatic(MyConstants.class);
	    }

	    @Test
	    public void mockStaticMethod() throws Exception {
	    	Assert.assertNotNull(mySpringBean);
	        when(MyConstants.getInt()).thenReturn(100);
	        int r = MyConstants.getInt();
	        System.out.println(r);

	    }

}
