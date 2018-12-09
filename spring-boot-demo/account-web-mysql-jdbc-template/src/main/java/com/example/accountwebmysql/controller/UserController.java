package com.example.accountwebmysql.controller;

import com.example.accountwebmysql.dao.UserDao;
import com.example.accountwebmysql.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable("userId") String userId){
        return userDao.getUserById(userId);
    }

}
