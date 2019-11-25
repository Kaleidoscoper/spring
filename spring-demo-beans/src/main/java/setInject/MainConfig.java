package setInject;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//以下是几种Bean注入方式
@ComponentScan(basePackages = {"setInject"})
//@Import(value = {InstC.class, TulingImportBeanDefinitionRegister.class,TulingImporSelector.class})
//@Import(value = {InstC.class})
//@Import(value = {TulingImportBeanDefinitionRegister.class})
@Import(value = {TulingImporSelector.class})
@Configuration
public class MainConfig {
}
