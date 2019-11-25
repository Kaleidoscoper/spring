package setInject;

import org.springframework.stereotype.Component;

@Component
public class Person {
	private String userName;
	private Integer age;

	public Person(String userName) {
		this.userName = userName;
		System.out.println("person的有参构造器-----------------userName");
	}

	public Person() {
		System.out.println("person的无参构造器");
	}

	public Person(Integer age) {
		this.age = age;
		System.out.println("person的有参构造器-----------------age");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
