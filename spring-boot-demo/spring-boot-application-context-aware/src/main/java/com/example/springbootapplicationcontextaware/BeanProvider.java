package com.example.springbootapplicationcontextaware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BeanProvider implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public List<String> getAllBeanNames(){
        return Arrays.stream(context.getBeanDefinitionNames())
                .sorted((a,b)->b.compareToIgnoreCase(a))
                .collect(Collectors.toList());
    }

    public List<String> getAllControllerBeanNames(){
        return Arrays.stream(context.getBeanNamesForAnnotation(RestController.class))
                .collect(Collectors.toList());
    }
}
