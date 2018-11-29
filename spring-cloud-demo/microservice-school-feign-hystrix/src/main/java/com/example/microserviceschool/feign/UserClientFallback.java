package com.example.microserviceschool.feign;

import com.example.microserviceschool.sao.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserClientFallback implements UserClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserClientFallback.class);

    @Override
    public User getUserById(Integer userId) {
        LOGGER.trace("getUserById Fallback");
        return null;
    }

    @Override
    public ResponseEntity<List<User>> getUserList(Integer pageNum, Integer pageSize) {
        LOGGER.trace("getUserList Fallback");
        return null;
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        LOGGER.trace("createUser Fallback");
        return null;
    }

    @Override
    public ResponseEntity<User> updateUser(User newUser) {
        LOGGER.trace("updateUser Fallback");
        return null;
    }

    @Override
    public ResponseEntity<User> deleteUser(Integer userId) {
        LOGGER.trace("deleteUser Fallback");
        return null;
    }
}
