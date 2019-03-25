package IoCAnnotationConfigAutowiring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TestLifecycleBean implements InitializingBean,DisposableBean {

    //1. 第一执行
    //java9 不支持@PostConstruct @PreDestroy
    //java8支持，可以切换成1.8。
    //由触发执行 InitDestroyAnnotationBeanPostProcessor
    @PostConstruct
    public void test01(){
        System.out.println("TestLifecycleBean---------@PostConstruct");
    }

    @PreDestroy
    public void test02(){
        System.out.println("TestLifecycleBean---------@PreDestroy");
    }

    //2. 第二执行
    //缺点：需要实现InitializingBean接口，与Spring耦合
    //由AbstractAutowireCapableBeanFactory 触发执行。
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestLifecycleBean---------afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("TestLifecycleBean---------destroy");
    }


    //3. 第三执行
    //这种官方推荐，在配置bean时需要显示指定函数名。
    //由AbstractAutowireCapableBeanFactory 触发执行
    public void initMethod(){
        System.out.println("TestLifecycleBean---------initMethod");
    }


    public void destroyMethod(){
        System.out.println("TestLifecycleBean---------destroyMethod");
    }

}
