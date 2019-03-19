package springbootmvc;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SpringBootMVCMain {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args){
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(6666);
        tomcat.getConnector();

        //tomcat.addContext("/", "C://test001");

        //必须指定tomcat为一个web应用，sci接口才能被回调。
        tomcat.addWebapp("/","C://test001");
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

        tomcat.getServer().await();
    }
}
