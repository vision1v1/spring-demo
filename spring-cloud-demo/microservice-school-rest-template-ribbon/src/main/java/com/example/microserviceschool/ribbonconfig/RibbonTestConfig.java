package com.example.microserviceschool.ribbonconfig;

import com.example.microserviceschool.ExcludeFromComponentScan;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExcludeFromComponentScan
public class RibbonTestConfig {

    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }


}
