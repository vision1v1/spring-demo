package springaopusage;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* transfer(..))")// the pointcut expression
    private void anyOldTransfer() {
        System.out.println("anyOldTransfer");
    }// the pointcut signature

    @Pointcut("within(springaopusage..*)")
    public void inWebLayer() {
        System.out.println("inWebLayer");
    }
}
