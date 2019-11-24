package demo;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.aop.framework.AopContext;

public class CalculateImpl implements Calculate {
	@Override
	public int add(int a, int b) {
		System.out.println("执行目标方法:add");
		return a + b;
	}

	@Override
	public int reduce(int a, int b) {
		System.out.println("执行目标方法:reduce");
		return a - b;
	}

	@Override
	public int multi(int a, int b) {
		System.out.println("执行目标方法:multi");
		return a * b;
	}

	@Override
	public int divide(int a, int b) {
		System.out.println("执行目标方法:multi");
		return a / b;
	}

	/**
	 * Aop中的特殊情况：
	 * 1、mod方法钟调用add方法，默认add方法是不会增强的，如果想增强，就要在MainConfig类上设置成
	 * @EnableAspectJAutoProxy(exposeProxy = true)，原理是会暴露代理对象到线程变量中，调用的时候
	 * 用((Calculate) AopContext.currentProxy())把代理对象取出来，只要用代理对象执行add就会执行对应Advisor
	 * 2、同理事务方法a调用事务方法b也是同样的道理
	 * @param a
	 * @param b
	 * @return
	 */
	@Override
	public int mod(int a, int b) {
		System.out.println("执行目标方法:mod");
//		int retVal = ((Calculate) AopContext.currentProxy()).add(a,b);//调用代理对象，会切两次,注意如果xposeProxy = false这样写会编译报错 Cannot find current proxy..
		int retVal = this.add(a,b);//切面只会切一次
		return retVal;
	}
}
