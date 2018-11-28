package com.example.microserviceschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
public class MicroserviceSchoolRestTemplateApplication {

    // @LoadBalanced 标记必须加上，否则restTemplate 不能自动根据Eureka查找服务实例
    // issues https://github.com/spring-cloud/spring-cloud-netflix/issues/1119
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceSchoolRestTemplateApplication.class, args);
    }
}
