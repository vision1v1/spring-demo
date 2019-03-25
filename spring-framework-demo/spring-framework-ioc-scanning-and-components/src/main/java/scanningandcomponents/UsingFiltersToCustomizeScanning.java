package scanningandcomponents;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import scanningandcomponents.annotations.School;

import java.util.Arrays;

public class UsingFiltersToCustomizeScanning {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        String[] beanNamesForComponent = context.getBeanNamesForAnnotation(Component.class);

        Arrays.stream(beanNamesForComponent).forEach(System.out::println);

        System.out.println("--------------------------------");

        String[] beanNamesForSchool = context.getBeanNamesForAnnotation(School.class);

        Arrays.stream(beanNamesForSchool).forEach(System.out::println);

        System.out.println("--------------------------------");

        AppConfig proxyByCGLib = context.getBean(AppConfig.class);

        System.out.println(proxyByCGLib.getClass());

    }
}
