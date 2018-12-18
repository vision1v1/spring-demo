package IoCAnnotationConfigAutowiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IoCAnnotationConfigAutowiringMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);

        for (int i = 0; i < 10; i++) {
            MyController01 controller01 = context.getBean("controller01", MyController01.class);
            System.out.println(i + " : " + controller01.getService());
        }

        ((AnnotationConfigApplicationContext) context).close();
    }
}
