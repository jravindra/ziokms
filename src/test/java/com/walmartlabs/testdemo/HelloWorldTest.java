package com.walmartlabs.testdemo;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:beans.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@PrepareForTest({ MyConstants.class })
@Category({UnitTests.class})
public class HelloWorldTest {

	@Rule
	public PowerMockRule rule = new PowerMockRule();

	@Autowired
	MySpringBean mySpringBean;

	@Mock
	MyMockBean myEasyMockBean;

	MyAnotherSpringBean myAnotherSpringBean;

	@Before
	public void setUp() throws Exception {
		setUpMyAnotherSpringBeanMock();
	}

	private void setUpMyAnotherSpringBeanMock() {
		MockitoAnnotations.initMocks(this);
		mockStatic(MyConstants.class);
        when(MyConstants.getInt()).thenReturn(100);
        when(myEasyMockBean.returnInt()).thenReturn(14);
	}

	@Test
	public void testMyBean() {

		Assert.assertNotNull(mySpringBean);
		System.out.println(mySpringBean.getName());
		int r = myEasyMockBean.returnInt();
		System.out.println(r);
		final int i = MyConstants.getInt();
		System.out.println(i);
	}

	public MyMockBean getMyEasyMockBean() {
		return myEasyMockBean;
	}

	public void setMyEasyMockBean(MyMockBean myEasyMockBean) {
		this.myEasyMockBean = myEasyMockBean;
	}

	public MyAnotherSpringBean getMyAnotherSpringBean() {
		return myAnotherSpringBean;
	}

	public void setMyAnotherSpringBean(MyAnotherSpringBean myAnotherSpringBean) {
		this.myAnotherSpringBean = myAnotherSpringBean;
	}

}
