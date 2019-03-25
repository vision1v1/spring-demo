package scanningandcomponents.components;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import scanningandcomponents.beans.HotGirl;

import java.lang.reflect.Member;

@Component
public class ConfigComponent {
    @Bean(name = "hot-girl")
    @Qualifier("public")
    public HotGirl publicInstance(){
        return new HotGirl("public-girl",18);
    }

    // 该示例将int方法参数age自动装配到另一个名为privateInstance的bean上的age属性值。
    // Spring Expression Language元素通过符号＃{<expression>}定义属性的值。
    // 对于@Value注释，表达式解析器预先配置为在解析表达式文本时查找bean名称。
    @Bean(name = "nice-girl")
    protected HotGirl protectedInstance(@Qualifier("public") HotGirl girl, @Value("#{beautifulGirl.age}") int age){
        HotGirl hotGirl = new HotGirl("protected-girl",20);
        hotGirl.setNextGirl(girl);
        hotGirl.setAge(age);
        return hotGirl;
    }


    @Bean(name = "beautifulGirl")
    // @Bean(name = "beautiful-girl")
    // 注意这里如果命名为"beautiful-girl" 上面@Value("#{beautiful-girl.age}") 就会注入失败。
    // 也就是说，这里的bean的命名不能有"-"。
    private HotGirl privateInstance(){
        HotGirl hotGirl = new HotGirl("private-girl",22);
        return hotGirl;
    }

    @Bean//(name = "ppGirl")
    @Scope("prototype")
    public HotGirl prototypeInstance(InjectionPoint injectionPoint){
        Member member = injectionPoint.getMember();
        return new HotGirl("prototype-Instance for " + member.getName(),25);
    }



}
