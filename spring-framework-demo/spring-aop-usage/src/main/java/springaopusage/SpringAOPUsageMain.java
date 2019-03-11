package springaopusage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAOPUsageMain {

    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BusinessTarget targetBean = context.getBean(BusinessTarget.class);

        targetBean.transfer();

        targetBean.transfer("hehe");

    }
}
