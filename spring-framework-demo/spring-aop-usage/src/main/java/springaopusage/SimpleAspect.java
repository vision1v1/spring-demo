package springaopusage;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SimpleAspect {

    @Before(value = "execution(* springaopusage.SimplePojo.add(..))")
    public void beforeAdd(){
        System.out.println("beforeAdd");
    }

    @AfterReturning(
            value = "execution(public int springaopusage.SimplePojo.add(..))",
            //argNames = "a,b",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint,int result){
        Object[] args = joinPoint.getArgs();
        System.out.println(args[0]  + " + " + args[1] + " 计算结果 : " + result);
    }

}
