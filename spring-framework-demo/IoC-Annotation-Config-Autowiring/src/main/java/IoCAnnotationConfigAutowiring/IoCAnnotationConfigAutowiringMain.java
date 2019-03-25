package IoCAnnotationConfigAutowiring;

import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

public class IoCAnnotationConfigAutowiringMain {

    public static void main(String[] args) {
//        test01();
        //       test02();
//        test03();
        //test04();
        test05();
//        test06();
//        test07();
    }

    public static void test01() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);

        for (int i = 0; i < 10; i++) {
            MyController01 controller01 = context.getBean("controller01", MyController01.class);
            System.out.println(i + " : " + controller01.getService());
        }

        ((AnnotationConfigApplicationContext) context).close();
    }

    public static void test02() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ClientService clientService = annotationConfigApplicationContext.getBean("clientService", ClientService.class);

        System.out.println(clientService.add(1, 2));
    }

    public static void test03() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        SingletonBean singletonBean = annotationConfigApplicationContext.getBean("singletonBean", SingletonBean.class);
        PrototypeBean prototypeBean1 = singletonBean.createPrototypeBean();

        PrototypeBean prototypeBean2 = singletonBean.getPrototypeBean();


    }

    public static void test04() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Scope customScope = new CustomScope();
        annotationConfigApplicationContext.getBeanFactory().registerScope("custom", customScope);
    }

    public static void test05(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        TestLifecycleBean bean = annotationConfigApplicationContext.getBean(TestLifecycleBean.class);

    }

    public static void test06(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("IoCAnnotationConfigAutowiring");


        TestController controller = annotationConfigApplicationContext.getBean(TestController.class);

        System.out.println(controller.getResult(1, 2));

        controller.computeResult(1,1);

    }

    //测试所有property的使用
    public static void test07(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);

        ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();

        MutablePropertySources propertySources = environment.getPropertySources();

        System.out.println("---------属性源如下---------");
        propertySources.forEach(ps ->{
            System.out.println(ps);
        });
        System.out.println("--------------------------");

        TestController controller = annotationConfigApplicationContext.getBean(TestController.class);

        System.out.println(controller.getResult(1, 2));

        controller.computeResult(1,1);

    }
}
