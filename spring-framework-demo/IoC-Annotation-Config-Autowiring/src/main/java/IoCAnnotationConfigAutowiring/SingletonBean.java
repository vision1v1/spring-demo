package IoCAnnotationConfigAutowiring;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope(BeanDefinition.SCOPE_SINGLETON)
public abstract class SingletonBean {


    @Lookup(value = "prototypeBean")
    public abstract PrototypeBean getPrototypeBean();


    //如果依赖一个 scope为原型的bean时。
    @Lookup
    protected PrototypeBean createPrototypeBean(){
        //请注意，SingletonBean中，我们将createPrototypeBean方法保留为存根。
        //这是因为Spring通过调用beanFactory.getBean（PrototypeBean.class）来覆盖该方法，因此我们可以将其保留为空。
        return null;
    }
}
