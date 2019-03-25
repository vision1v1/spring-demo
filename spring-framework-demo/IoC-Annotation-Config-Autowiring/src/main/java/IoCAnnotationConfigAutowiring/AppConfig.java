package IoCAnnotationConfigAutowiring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@ComponentScan("IoCAnnotationConfigAutowiring")
public class AppConfig {

    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public TestLifecycleBean testLifecycleBean(){
        return new TestLifecycleBean();
    }

    //PropertyOverrideConfigurer
}
