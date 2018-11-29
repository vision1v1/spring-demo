package com.example.microserviceschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class MicroserviceSchoolFeignHystrixApplication {

    @Bean
    public StashErrorDecoder errorDecoder(){
        return new StashErrorDecoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceSchoolFeignHystrixApplication.class, args);
    }
}
