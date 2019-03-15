package springbootminisci;

import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 在web环境下
 * */
@HandlesTypes(MiniWebApplicationInitializer.class)
public class SpringMiniServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> miniWebApplicationInitializers, ServletContext ctx) throws ServletException {

        System.out.println("--------SpringMiniServletContainerInitializer-----------");

        List<MiniWebApplicationInitializer> initializers = new LinkedList<>();

        if(miniWebApplicationInitializers != null){
            for (Class<?> mwaiClass : miniWebApplicationInitializers) {
                if (!mwaiClass.isInterface() && !Modifier.isAbstract(mwaiClass.getModifiers()) &&
                        MiniWebApplicationInitializer.class.isAssignableFrom(mwaiClass)) {
                    try {
                        initializers.add((MiniWebApplicationInitializer)
                                ReflectionUtils.accessibleConstructor(mwaiClass).newInstance());
                    }
                    catch (Throwable ex) {
                        throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
                    }
                }
            }
        }

        if (initializers.isEmpty()) {
            ctx.log("No Spring WebApplicationInitializer types detected on classpath");
            return;
        }

        ctx.log(initializers.size() + " Spring WebApplicationInitializers detected on classpath");
        AnnotationAwareOrderComparator.sort(initializers);
        for (MiniWebApplicationInitializer initializer : initializers) {
            initializer.onStartup(ctx);
        }
    }
}
