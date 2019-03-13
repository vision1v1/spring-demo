package com.example.accountwebmysqlmybatis;

import com.example.accountwebmysqlmybatis.entity.User;
import com.example.accountwebmysqlmybatis.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AccountWebMysqlMybatisApplicationTestMain {
    public static void main(String[] args){
        test01();
    }

    public static void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        List<User> userList = userService.getUserList(1,10);
        userList.forEach(System.out::println);
    }
}
