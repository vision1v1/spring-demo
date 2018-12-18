package com.example.webautoconfigure;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;


@SpringBootApplication
public class WebAutoconfigureApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(WebAutoconfigureApplication.class, args);

        PrintAllBeans(context);

        PrintAllBeanScope(context);

    }

    public static void PrintAllBeans(ApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        Arrays.sort(names);
        for (String name : names) {
            Object bean = context.getBean(name);
            System.out.println(String.format("name : [%s] object [%s] hashcode [%s]",name,bean,bean.hashCode()));
        }
    }

    public static void PrintAllBeanScope(ApplicationContext context) {
        if(context instanceof GenericApplicationContext){
            String[] names = context.getBeanDefinitionNames();
            Arrays.sort(names);
            for (String name : names) {
                BeanDefinition beanDefinition = ((GenericApplicationContext)context).getBeanDefinition(name);
                System.out.println(String.format("name : [%s] scope : [%s]",name,beanDefinition.getScope()));
            }
        }
    }

}

