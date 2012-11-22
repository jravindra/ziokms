package com.walmartlabs.testdemo;

import javax.annotation.Resource;

public class MyMockBean {
	
	@Resource
	private MyAnotherSpringBean myAnotherSpringBean;
	
	public void returnNothing(String a) {
		myAnotherSpringBean.setNum(10);
		final int num = myAnotherSpringBean.getNum();
	}
	
	public int returnInt() {
		return MyConstants.getInt();
	}
	
	public int returnAnotherInt() {
		final int num = myAnotherSpringBean.getNum();
//		System.out.println(num);
		return num;
	}

	public MyAnotherSpringBean getMyAnotherSpringBean() {
		return myAnotherSpringBean;
	}

	public void setMyAnotherSpringBean(MyAnotherSpringBean myAnotherSpringBean) {
		this.myAnotherSpringBean = myAnotherSpringBean;
	}
}
