package com.whj;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * IOC容器完成的功能是依赖注入，控制反转，不光是单例缓存池还有其他包括BeanDefinitionMap、后置处理器等等一些其他组件
 */
public class MainStarter {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		Person person = (Person) context.getBean("person");
		System.out.println("Person" + person);
	}
}
