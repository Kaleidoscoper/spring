package demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
		/**
		 * 实际是代理对象
		 * 1、代理对象存放在哪里
		 * 2、代理对象什么时候生成
		 * 3、我们的目标对象在哪里
		 */
		Calculate calculate = (Calculate) ctx.getBean("calculate");
//		calculate.mod(9,8);
		calculate.divide(9,0);

	}
}
