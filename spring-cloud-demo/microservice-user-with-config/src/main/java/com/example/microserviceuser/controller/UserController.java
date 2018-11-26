package com.example.microserviceuser.controller;

import com.example.microserviceuser.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${first}")
    private String firstValue;


    @SuppressWarnings("Duplicates")
    @RequestMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId) {
        LOGGER.trace("get user {}", userId);
        LOGGER.debug("get user {}", userId);
        LOGGER.info("get user {}", userId);
        LOGGER.warn("get user {}", userId);
        LOGGER.error("get user {}", userId);
        User user = new User();
        user.setUserId(userId);
        user.setName("happy engineer");
        user.setAge(100);
        user.setEmail("test@123.com");
        return user;
    }

    @RequestMapping("/first")
    public String getFirst(){
        return firstValue;
    }


}
