package setInject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainClass {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
//		System.out.println(ctx.getBean(InstA.class));
//		InstD instD = ctx.getBean(InstD.class);
//		InstD instD = (InstD)ctx.getBean("instA");//偷梁换柱了不会报错
		InstA instA = ctx.getBean(InstA.class);//如果偷梁换柱了，这里会报错，因为容器中没有InstA
		System.out.println(instA);
	}
}
