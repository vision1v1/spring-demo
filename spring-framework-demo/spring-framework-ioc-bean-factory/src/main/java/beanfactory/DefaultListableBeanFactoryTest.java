package beanfactory;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

import java.util.Arrays;


public class DefaultListableBeanFactoryTest {

    public static void main(String[] args){

        //XmlBeanDefinitionReader

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(factory);

        reader.register(AppConfig.class);

        factory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());

        System.out.println("--------all bean definitions----------");

        Arrays.stream(factory.getBeanDefinitionNames()).forEach(System.out::println);

        System.out.println("--------------------------------------");

        AppConfig config = factory.getBean("appConfig",AppConfig.class);

        System.out.println(config);

    }
}
