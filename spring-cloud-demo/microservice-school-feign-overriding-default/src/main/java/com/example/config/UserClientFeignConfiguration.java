package com.example.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration #官网说可以不用加上
public class UserClientFeignConfiguration {

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

}
