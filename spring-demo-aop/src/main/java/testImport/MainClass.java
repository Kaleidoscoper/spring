package testImport;

import testImport.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
		for (String beanName:ctx.getBeanDefinitionNames()){
			System.out.println("beanName:"+beanName);
		}
	}
}
