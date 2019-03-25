package IoCAnnotationConfigAutowiring;

import IoCAnnotationConfigAutowiring.Annotation.ComputeQulifier;
import IoCAnnotationConfigAutowiring.Annotation.Offline;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)//bean的顺序
@Offline//候选资格注解，这里是注解的名称
@ComputeQulifier(type = ComputeType.CH)
public class JackComputeService implements ComputeService {
    @Override
    public int sub(int a, int b) {
        System.out.println("Jack 计算服务");
        return a+b;
    }
}
