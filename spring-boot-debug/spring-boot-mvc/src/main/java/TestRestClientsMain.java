import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponents;
import springbootmvc.AppConfig;
import springbootmvc.controllers.TestController;
import springbootmvc.response.User;

import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

public class TestRestClientsMain {
    public static void main(String[] args){
        testRestTemplate();
        //test01();
    }

    public static void testRestTemplate(){
        String baseUrl = "http://localhost:6666";
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);


        RestTemplate restTemplate = new RestTemplate();
        //AppConfig appConfig = new AppConfig();
        //restTemplate.getMessageConverters().add(appConfig.mappingJackson2HttpMessageConverter());
        restTemplate.setUriTemplateHandler(factory);

        Object user = restTemplate.getForObject("test/user", Object.class);
        System.out.println(user);
    }

    // starter-webflux
    // reactor-spring
    public static void testWebClient(){


    }

//    public static void test01(){
//        UriComponents uriComponents = MvcUriComponentsBuilder
//                .fromMethodCall(on(TestController.class).getUser()).buildAndExpand();
//
//        URI uri = uriComponents.encode().toUri();
//        System.out.println(uri);
//    }
}
