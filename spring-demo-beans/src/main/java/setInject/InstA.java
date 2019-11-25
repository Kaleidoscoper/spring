package setInject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BeanDefinition中autowireMode属性解释
 * 即Bean的注入模型:
 * 1、AUTOWIRE_NO = 0 默认(Bean依赖的组件必须显示指定@Autowired才能注入容器)
 * 2、AUTOWIRE_BY_NAME = 1 Set注入，根据名称去找,容错处理低，必须名称一致才能找到
 * 3、AUTOWIRE_BY_TYPE = 2 Set注入，可以不加@Autowired，但必须提供setInstB方法，容错处理高，名称不一致类型一致也可以
 * 4、AUTOWIRE_CONSTRUCTOR = 3 构造器注入模式
 * bean定义---->beanFactoryPostProcessor(修改bean定义)------>getBean(获取Bean定义，生成bean)--------->BeanPostProcessor(初始化前后，初始化包括)
 * 1）bean实现了InitializingBean接口，对应的方法为afterPropertiesSet
 * 2）在bean定义的时候，通过init-method设置的方法
 * ------>ean实例化好放入单例缓存池
 */
@Component
public class InstA {
//	@Autowired
	private InstB instB;

	public InstB getInstB() {
		return instB;
	}

	public void setInstB(InstB instB) {
		this.instB = instB;
	}

	public InstA() {
		System.out.println("InstA 的构造方法.....");
	}

	@Override
	public String toString() {
		return "InstA{" +
				"instB=" + instB +
				'}';
	}
}
