package springaopusage;


import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    //切点
    @Pointcut("execution(* springaopusage.BusinessTarget.transfer(..))")// the pointcut expression
    private void anyOldTransfer() {
        System.out.println("anyOldTransfer");
    }// the pointcut signature

    @Pointcut("within(springaopusage..*)")
    public void inWebLayer() {
        System.out.println("inWebLayer");
    }


    //advice 通知
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
