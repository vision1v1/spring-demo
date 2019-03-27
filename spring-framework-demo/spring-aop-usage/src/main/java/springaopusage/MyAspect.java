package springaopusage;


import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
//3.切面
@Aspect
public class MyAspect {
    //Spring AOP 关键概念
    //1.切点。切点表达式，切点签名。
    //2.通知。就是要执行增强逻辑的函数。
    //3.切面，就是包含增强逻辑的类。



    //1.切点
    // the pointcut expression。"" 中的是切点表达式
    @Pointcut("execution(* springaopusage.BusinessTarget.transfer(..))")
    // the pointcut signature，这个方法就当作切点的签名，返回类型必须是void。参数与方法体都忽略。
    private void anyOldTransfer() {
        System.out.println("anyOldTransfer");
    }




    @Pointcut("within(springaopusage..*)")
    public void inWebLayer() {
        System.out.println("inWebLayer");
    }


    //2.advice 通知
    //@Before("anyOldTransfer()") //切点与通知分开写法，
    @Before("execution(* springaopusage.BusinessTarget.transfer(..))")// 切点与通知写在一起的写法
    public void beforeTransfer(){
        System.out.println("beforeTransfer");
    }

    @After("inWebLayer()")
    public void afterTransfer(){
        System.out.println("afterTransfer");
    }

    //也可以将通知与连接点写在一起，
//    @Around("execution(* springaopusage.BusinessTarget.transfer(..))")
//    public void aroundTransfer(){
//        System.out.println("aroundTransfer");
//    }
}
