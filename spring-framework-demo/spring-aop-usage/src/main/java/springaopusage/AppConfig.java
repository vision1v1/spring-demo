package springaopusage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy
@Configuration
public class AppConfig {

    @Bean(name = "target")
    public BusinessTarget businessTarget(){
        return new BusinessTarget();
    }
}
