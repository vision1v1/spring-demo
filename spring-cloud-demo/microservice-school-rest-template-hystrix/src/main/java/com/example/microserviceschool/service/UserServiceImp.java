package com.example.microserviceschool.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class UserServiceImp implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    // Hystrix Configuration : https://github.com/Netflix/Hystrix/wiki/Configuration

    @HystrixCommand(fallbackMethod = "testHystrixFallback")
//    @HystrixCommand(fallbackMethod = "testHystrixFallback",
//            commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")})
    //default THREAD
//    @HystrixCommand(fallbackMethod = "testHystrixFallback",
//            commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")})
    public String testHystrix(){
        LOGGER.trace("--------testImp Thread Id : {} --------", Thread.currentThread().getId());
        throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    private String testHystrixFallback(Throwable e) {
        LOGGER.trace("--------testHystrixFallback Thread Id : {} --------", Thread.currentThread().getId());
        return "hi I'm testHystrix I Circuit Break message : " + e.getMessage();
    }
}
