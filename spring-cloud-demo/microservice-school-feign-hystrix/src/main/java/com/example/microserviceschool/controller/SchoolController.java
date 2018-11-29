package com.example.microserviceschool.controller;

import com.example.microserviceschool.feign.UserClient;
import com.example.microserviceschool.sao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    UserClient userClient;

    @RequestMapping("/student/list")
    public ResponseEntity<List<User>> getStudents(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        ResponseEntity<List<User>> response = userClient.getUserList(pageNum, pageSize);
        List<User> userList = response.getBody();
        return ResponseEntity.ok(userList);
    }

    @RequestMapping("/student/{studentId}")
    public ResponseEntity<User> getStudentById(@PathVariable("studentId") Integer studentId) {
        User user = userClient.getUserById(studentId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/student")
    public ResponseEntity<User> createStudent(@RequestBody User user) {
        ResponseEntity<User> responseEntity = userClient.createUser(user);
        User createdUser = responseEntity.getBody();
        return ResponseEntity.created(URI.create("/student/" + createdUser.getUserId())).body(createdUser);
    }

    @PutMapping("/student")
    public ResponseEntity<User> updateStudent(@RequestBody User user) {
        ResponseEntity<User> responseEntity = userClient.updateUser(user);
        //默认情况下 feign 调用其它的微服务接口返回404 认为是异常。
        //那么如何定义状态码符合restfull，需要自己扩展
        //wiki https://github.com/OpenFeign/feign/wiki/Custom-error-handling
        //我的提问 https://github.com/OpenFeign/feign/issues/848
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<User> deleteStudent(@PathVariable("studentId") Integer studentId) {
        ResponseEntity<User> responseEntity = userClient.deleteUser(studentId);
        return ResponseEntity.ok(responseEntity.getBody());
    }
}
