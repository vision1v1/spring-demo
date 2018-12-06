package com.example.accountwebmysqlmybatis.controller;

import com.example.accountwebmysqlmybatis.dto.CreateUserInput;
import com.example.accountwebmysqlmybatis.dto.UpdateUserInput;
import com.example.accountwebmysqlmybatis.entity.User;
import com.example.accountwebmysqlmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUsers(@RequestParam int pageNum, @RequestParam int pageSize) {
        List<User> users = userService.getUserList(pageNum, pageSize);
        return ResponseEntity.ok(users);
    }

    @PostMapping("")
    public ResponseEntity createUser(@RequestBody CreateUserInput input) {
        String userId = userService.createUser(input);
        return ResponseEntity.created(URI.create("users/" + userId)).build();
    }

    @PutMapping("")
    public ResponseEntity updateUser(@RequestBody UpdateUserInput input) {
        boolean updateResult = userService.updateUser(input);
        return updateResult ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        boolean deleteResult = userService.deleteUser(userId);
        return deleteResult ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
