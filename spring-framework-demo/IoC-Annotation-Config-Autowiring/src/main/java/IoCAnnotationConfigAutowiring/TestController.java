package IoCAnnotationConfigAutowiring;

import IoCAnnotationConfigAutowiring.Annotation.ComputeQulifier;
import IoCAnnotationConfigAutowiring.Annotation.Genre;
import IoCAnnotationConfigAutowiring.Annotation.Offline;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

@Component
public class TestController {
    // @Autowired，@ Inject，@ Resource和@Value注释由Spring BeanPostProcessor实现处理。
    // 这意味着您无法在自己的BeanPostProcessor或BeanFactoryPostProcessor类型（如果有）中应用这些注释。
    // 必须使用XML或Spring @Bean方法显式地“连接”这些类型。

    private String name;

    //也是由AutowiredAnnotationBeanPostProcessor postProcessProperties设置的。
    //一般写法
    @Value("#{'${test.name}'}")
    //@Value("#{my['test.name']?:'456'}")
    private void setName(String name){
        this.name = name;
        System.out.println("设置 value : " + name);
    }



    private BeanFactory beanFactory;

    private void injectBeanFacotry(@Autowired BeanFactory beanFactory){
        this.beanFactory = beanFactory;
        System.out.println("我是 beanFactory : " + beanFactory.getClass());
    }


    private ApplicationContext applicationContext;

    //也是由AutowiredAnnotationBeanPostProcessor postProcessProperties设置的。
    @Autowired
    private void injectApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
        System.out.println("我是 applicationContext : " + applicationContext.getClass());
    }


    private ComputeService computeService;

    // 如果有多个bean,类型为ComputeService,注入时会出现NoUniqueBeanDefinitionException，不明确注入异常。
    // 解决办法有两种
    // 1，利用@Qualifier("tomComputeService")，明确指明，使用哪个实现。
    // 2，在bean的类上标记@Primary，告诉IoC，用使用那个bean为首选的。
    @Autowired
    @Qualifier("tomComputeService")
    private void setComputeService(ComputeService computeService){
        this.computeService = computeService;
        System.out.println("I'm a " + computeService.getClass());
    }


    private ComputeService computeService1;

    //通过 CommonAnnotationBeanPostProcessor postProcessProperties 注入进来
    @Resource(name = "jackComputeService")
    private void injectComputerService1(ComputeService computeService){
        this.computeService1 = computeService;
        System.out.println("我是通过@Resource方式注入进来的 " + computeService.getClass());
    }



    private ComputeService computeService2;
    //由AutowiredAnnotationBeanPostProcessor postProcessProperties设置的。
    @Autowired
    private void injectComputerService02(@Genre(value = "jackComputeService") ComputeService computeService2){
        this.computeService2 = computeService2;
        System.out.println("我是通过自定义注解@Genre方式注入进来的 " + computeService2.getClass());
    }



    private ComputeService computeService3;
    //由AutowiredAnnotationBeanPostProcessor postProcessProperties设置的。
    @Autowired
    private void injectComputerService03(@Offline ComputeService computeService3){
        this.computeService3 = computeService3;
        System.out.println("我是通过自定义注解@Offline方式注入进来的 " + computeService3.getClass());
    }


    private ComputeService computeService4;
    @Autowired
    @ComputeQulifier(type = ComputeType.CH)
    private void injectComputerService04(ComputeService computeService4){
        this.computeService4 = computeService4;
        System.out.println("我是通过自定义注解@ComputeQulifier方式注入进来的 " + computeService4.getClass());
    }


    private Map<String,ComputeService> computeServiceMap;

    @Autowired
    public void setComputeServiceMap(Map<String,ComputeService> computeServiceMap){
        this.computeServiceMap = computeServiceMap;
        computeServiceMap.keySet().stream().forEach(key->{
            System.out.println("setComputeServiceMap 注入成功 key : " + key);
        });
    }



    private ComputeService[] computeServices;

    //ComputeService 类型的所有的bean都可以注入进来。
    @Autowired
    public void setComputeServices(ComputeService[] computeServices) {
        this.computeServices = computeServices;
    }

    //字段注入不推荐。
    //AutowiredAnnotationBeanPostProcessor postProcessMergedBeanDefinition 负责完成Autowired的功能。
    @Autowired
    private ClientService clientService;

    public TestController(){

    }

    //由AbstractAutowireCapableBeanFactory createBeanInstance时注入的。
//    @Autowired
//    public TestController(ClientService clientService){
//        this.clientService = clientService;
//    }

    //@Required SpringFramework 5.1 版本已经废弃了,推荐使用@Autowired
    //AutowiredAnnotationBeanPostProcessor 负责完成Autowired的功能。
//    @Autowired
//    public void setClientService123456(ClientService clientService){
//        this.clientService = clientService;
//    }





    public int getResult(int a,int b){
        return clientService.add(a,b);
    }

    public void computeResult(int a,int b){
        Arrays.stream(computeServices).forEach(cs-> cs.sub(a,b));
    }
}
