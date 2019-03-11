package springbootminisci;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SpringApplication {

    public static void run(){
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(6666);
        tomcat.getConnector();

        tomcat.addContext("/", "C://test001");
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

        tomcat.getServer().await();
    }
}
