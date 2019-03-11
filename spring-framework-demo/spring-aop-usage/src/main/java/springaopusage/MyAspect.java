package springaopusage;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
    @Before("anyOldTransfer()")
    public void beforeTransfer(){
        System.out.println("beforeTransfer");
    }

    @After("inWebLayer()")
    public void afterTransfer(){
        System.out.println("afterTransfer");
    }
}
