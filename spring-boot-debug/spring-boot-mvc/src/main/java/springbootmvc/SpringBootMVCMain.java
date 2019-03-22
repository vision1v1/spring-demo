package springbootmvc;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class SpringBootMVCMain {


    public static void main(String[] args){

        testTomcat();
        //testUriComponents();
    }

    @SuppressWarnings("Duplicates")
    public static void testTomcat(){
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

    public static void testUriComponents(){
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://example.com/hotels/{hotel}")
                .queryParam("q","{q}").queryParam("r","{r}")
                .encode()
                .build();
        URI uri = uriComponents.expand("Westin","123","456").toUri();
        System.out.println(uri);

        URI uri2 = UriComponentsBuilder
                .fromUriString("http://example.com/hotels/{hotel}?q={q}")
                .build("Westin", "123");

        System.out.println(uri2);
    }
}
