package demo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

/**
 * 一、怎么指定多个切面的执行顺序
 * 1、实现Ordered接口
 * 2、添加Ordered注解(value越小优先级越高，默认是Integer.MAX_VALUE)
 * 二、我们自定义切面和事务注解切面怎么控制他们的顺序
 * 我们观察@EnableTransactionManagement导入的切面的优先级也是最低的，所以依据解析顺序，事务切面在前(AnnotationAwareAspectJAutoProxyCreator:93)
 * 加入我们要控制的自定义切面，要想在事务前执行就要定义一个非Integer.MAX_VALUE的数字
 * 三、spring是如何解析切面的
 * 跟踪代码发现在bean的生命周期中
 * AbstractAutowireCapableBeanFactory#createBean中(还没有调用bean构造方法前)
 * 会调用bean的后置处理器InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation方法进行处理
 * 由于还没有目标对象(被代理对象生成)，所以该后置处理器不会去生成代理对象，但是会去解析切面，然后保存到缓存中
 * 四、具体怎么去解析切面的
 * 	1)、获取所有bean的名称，然后遍历通过bean的名称获取bean的class对象出来，然后判断classs上有没有标注
 * @Aspect,若标注了就认为当前class是切面类型,然后通过class.getmethod获取所有method上的注解，解析注解类型，switch判断before,after,around等
 * 创建对应的通知对象
 * 2)、由于容器去获取所有的bean名称去获取class对象，然后去解析切面信息，整个过程十分耗时，所以解析出来的通知对象会加入缓存中
 */
@Aspect
@Order(2)
public class LogAspect implements Ordered {
	@Override
	public int getOrder() {
		return 10;
	}

	@Pointcut("execution(* demo.CalculateImpl.*(..))")
	public void pointCut(){};

	@Before(value = "pointCut()")
	public void methodBefore(JoinPoint joinPoint) throws Throwable{
		String methodName = joinPoint.getSignature().getName();
		System.out.println("执行目标方法【" + methodName +"】的前置通知，入参:"+ Arrays.asList(joinPoint.getArgs()));
	}

	@After(value = "pointCut()")//即使出现异常的话也会调用
	public void methodAfter(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("执行目标方法【" + methodName +"】的后置通知，入参:"+ Arrays.asList(joinPoint.getArgs()));
	}

	@AfterReturning(value = "pointCut()",returning = "result")//如果出现异常的话return不会被调用
	public void methodReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("执行目标方法【" + methodName +"】的返回通知，入参:"+ Arrays.asList(joinPoint.getArgs()));
	}

	@AfterThrowing(value = "pointCut()")
	public void methodThrowing(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("执行目标方法【" + methodName +"】的异常通知，入参:"+ Arrays.asList(joinPoint.getArgs()));
	}
}
