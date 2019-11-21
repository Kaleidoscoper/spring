package com.whj;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class Person implements InitializingBean {
	private String name;
	private String sex;

	public void initPerson(){
		System.out.println("init 方法");
	}

	@PostConstruct
	public void init(){
		System.out.println("PostContruct init");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");
	}
}
