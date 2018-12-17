package IoCApplicationContextAnnotationConfigApplicationContext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IoCApplicationContextAnnotationConfigApplicationContextMain {

    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);

        for (int i = 0; i < 10; i++) {
            MyService01 service01 = context.getBean("service01", MyService01.class);
            BeanDefinition beanDefinition = ((AnnotationConfigApplicationContext) context).getBeanDefinition("service01");
            String scope = beanDefinition.getScope();
            System.out.println(String.format("%s----%s----%s----%s", i, service01, service01.hashCode(), scope));
        }

        ((AnnotationConfigApplicationContext) context).close();

    }
}
