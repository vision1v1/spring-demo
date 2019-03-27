package springaopusage;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.java2d.pipe.SpanShapeRenderer;

public class SpringAOPUsageMain {

    public static void main(String[] args){
        //test01();

        //test02();

        test03();

    }

    public static void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BusinessTarget targetBean = context.getBean(BusinessTarget.class);

        targetBean.transfer();

        //targetBean.transfer("hehe");
    }

    public static void test02(){

        ProxyFactory proxyFactory = new ProxyFactory(new SimplePojo());

        proxyFactory.addInterface(Pojo.class);

        proxyFactory.addAdvice(new RetryAdvice());

        Pojo pojo = (Pojo)proxyFactory.getProxy();

        pojo.foo();

    }

    public static void test03(){
        SimplePojo target = new SimplePojo();
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(target);
        aspectJProxyFactory.addAspect(SimpleAspect.class);
        Pojo pojo = aspectJProxyFactory.getProxy();
        System.out.println(pojo.add(88, 66));

        //Class clazz = SimpleAspect.class;
        //clazz.getConstantPool();

        //在Spring中创建AOP代理的基本方法是使用org.springframework.aop.framework.ProxyFactoryBean。 这样可以
        //org.springframework.aop.framework.ProxyFactoryBean
    }
}
