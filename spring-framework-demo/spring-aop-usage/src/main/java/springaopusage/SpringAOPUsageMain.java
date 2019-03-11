package springaopusage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAOPUsageMain {

    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("springaopusage");

        BusinessTarget targetBean = (BusinessTarget)context.getBean("target");

        targetBean.transfer();

        targetBean.transfer("hehe");

    }
}
