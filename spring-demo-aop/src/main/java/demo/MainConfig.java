package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@EnableAspectJAutoProxy相当于添加了个AnnotationAwareAspectJAutoProxyCreator组件，代理对象
//proxyTargetClass默认为false就是会走jdk动态代理，设置成true就是会走cglib即使实现了接口，没实现接口就会走cglib
//@EnableAspectJAutoProxy(proxyTargetClass = false)
/**
 * exposeProxy = true，会暴露代理对象到线程变量中，通知方法中互相调用，例如A中调用B会切两次。JdkDynamicAopProxy：189
 * 同理事务方法a调用事务方法b也是同样的道理，想让事务方法a调用事务方法b，就需要暴露代理对象
 */
@EnableAspectJAutoProxy(exposeProxy = true)
public class MainConfig {
	@Bean
	public Calculate calculate(){
		return new CalculateImpl();
	}

	@Bean
	public LogAspect logAspect(){
		return new LogAspect();
	}
}
