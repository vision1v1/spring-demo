package scanningandcomponents;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import scanningandcomponents.annotations.MyExclude;
import scanningandcomponents.annotations.School;

@Configuration
@ComponentScan(basePackages = "scanningandcomponents",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = School.class),
        excludeFilters = @ComponentScan.Filter(value = MyExclude.class)
)
public class AppConfig {


}
