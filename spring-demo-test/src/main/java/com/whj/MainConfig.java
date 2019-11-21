package com.whj;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class MainConfig {
	@Bean(initMethod = "initPerson")
	public Person person(){
		Person person = new Person();
		person.setName("hjw");
		person.setSex("male");
		return person;
	}
}
