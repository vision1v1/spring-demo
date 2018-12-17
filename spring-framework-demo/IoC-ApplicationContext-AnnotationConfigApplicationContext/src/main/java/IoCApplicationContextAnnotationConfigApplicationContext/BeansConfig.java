package IoCApplicationContextAnnotationConfigApplicationContext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class BeansConfig {

    @Bean("service01")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public MyService01 myService01(){
        return new MyService01();
    }
}
