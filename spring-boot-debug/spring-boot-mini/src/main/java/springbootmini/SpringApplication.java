package springbootmini;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringApplication {

    public static void run() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(6666);
        tomcat.getConnector();


        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);
        ac.refresh();


        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
//        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/app/*");


        tomcat.addContext("/", "C://test001");

        Wrapper wrapper = tomcat.addServlet("/", "app", servlet);
        wrapper.setLoadOnStartup(1);
        //http://localhost:6666/app/test/hi
        wrapper.addMapping("/app/*");


        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

        tomcat.getServer().await();

    }
}
