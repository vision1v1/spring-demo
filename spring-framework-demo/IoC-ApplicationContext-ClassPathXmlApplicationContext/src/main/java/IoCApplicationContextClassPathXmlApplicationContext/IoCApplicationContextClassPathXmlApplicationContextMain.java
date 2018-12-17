package IoCApplicationContextClassPathXmlApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCApplicationContextClassPathXmlApplicationContextMain {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        for (int i = 0; i < 10; i++) {
            MyService01 service01 = context.getBean("service01", MyService01.class);
            System.out.println(String.format("%s----%s----%s", i, service01, service01.hashCode()));
        }

        ((ClassPathXmlApplicationContext) context).close();
    }
}
