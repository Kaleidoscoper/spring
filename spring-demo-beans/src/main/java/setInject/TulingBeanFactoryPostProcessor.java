package setInject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;


@Component
public class TulingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition rootBeanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition("instA");
//		rootBeanDefinition.setBeanClass(InstD.class);//偷梁换柱，bean工厂的后置处理器把组件类型改成了InstD,所以容器中就没有InstA.class了

		rootBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
//		rootBeanDefinition.setBeanClass(InstD.class);
//		rootBeanDefinition.setLazyInit(true);

		/**
		 * 可以控制bean初始化时候用的构造器
		 */
		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition("person");
		ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
		constructorArgumentValues.addIndexedArgumentValue(0,"wanghongjie");
		genericBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);

	}
}
