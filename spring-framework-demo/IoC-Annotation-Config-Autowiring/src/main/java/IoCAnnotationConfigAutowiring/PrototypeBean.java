package IoCAnnotationConfigAutowiring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PrototypeBean {

    public PrototypeBean(){
        System.out.println(this + " " + "PrototypeBean");
    }

}
