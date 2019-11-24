package testImport.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import testImport.importSelect.TulingBeanDefinitionRegister;

@Configuration
@Import(value = {TulingBeanDefinitionRegister.class})
public class MainConfig {
}
