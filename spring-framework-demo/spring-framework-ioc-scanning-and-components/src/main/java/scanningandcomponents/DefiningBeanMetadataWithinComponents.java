package scanningandcomponents;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scanningandcomponents.beans.HotGirl;
import scanningandcomponents.components.ConfigComponent;

public class DefiningBeanMetadataWithinComponents {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigComponent.class);

        HotGirl girl = context.getBean("hot-girl", HotGirl.class);

        System.out.println(girl);

        System.out.println("--------------------------------------");

        HotGirl girl2 = context.getBean("nice-girl", HotGirl.class);

        System.out.println(girl2);

        System.out.println("--------------------------------------");

        HotGirl girl3 = context.getBean("prototypeInstance", HotGirl.class);

        System.out.println(girl3);
    }
}
