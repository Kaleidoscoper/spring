package com.whj;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.AliasFor;

class ComponentA {}

class ComponentB {}
@Configurable
public class MainConfig {
	@Bean(initMethod = "initPerson")
	public Person person(){
		Person person = new Person();
		person.setName("hjw");
		person.setSex("male");
		return person;
	}

	@Bean
	public ComponentA componentA(){
		return new ComponentA();
	}

	@Bean
	@DependsOn("componentA")
	public ComponentB componentB(){
		return new ComponentB();
	}
}
