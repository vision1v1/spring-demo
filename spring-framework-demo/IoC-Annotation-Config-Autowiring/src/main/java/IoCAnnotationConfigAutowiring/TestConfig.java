package IoCAnnotationConfigAutowiring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("IoCAnnotationConfigAutowiring")
@PropertySource(name = "my",value = "classpath:my.properties")
public class TestConfig {
}
