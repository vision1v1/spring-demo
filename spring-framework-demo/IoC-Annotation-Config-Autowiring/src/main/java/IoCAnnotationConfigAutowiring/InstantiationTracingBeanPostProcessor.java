package IoCAnnotationConfigAutowiring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

//@Component
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor, Ordered {

    //由AbstractAutowireCapableBeanFactory，触发调用。
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("****************************************************");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean '" + beanName + "' created : " + bean.toString());
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
