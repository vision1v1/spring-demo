package IoCAnnotationConfigAutowiring;

import IoCAnnotationConfigAutowiring.Annotation.ComputeQulifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 0)//相同类型的bean注册的顺序
@Primary// 有多个ComputerService 实现，使用@Primary作为首选注入
@ComputeQulifier(type = ComputeType.EN)
public class TomComputeService implements ComputeService {
    @Override
    public int sub(int a, int b) {
        System.out.println("Tom计算服务");
        return a-b;
    }
}
