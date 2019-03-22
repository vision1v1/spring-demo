package springbootmvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springbootmvc.controllers.UserHandler;

import java.lang.reflect.Method;

@Configuration
@ComponentScan("springbootmvc")
public class AppConfig implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("---------postProcessBeforeInitialization------------" + beanName + " " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("---------postProcessAfterInitialization----- -------" + beanName + " " + bean);
        return bean;
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        requestMappingHandlerAdapter.getMessageConverters().add(mappingJackson2HttpMessageConverter());
        return requestMappingHandlerAdapter;
    }

    @SuppressWarnings("Duplicates")
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

 //   @Autowired
//    public void setHandlerMapping(RequestMappingHandlerMapping mapping, UserHandler handler) throws NoSuchMethodException {
//        RequestMappingInfo info = RequestMappingInfo.paths("/user/{id}").methods(RequestMethod.GET).build();
//        Method method = UserHandler.class.getMethod("getUser",Long.class);
//        mapping.registerMapping(info,handler,method);
//    }


}
