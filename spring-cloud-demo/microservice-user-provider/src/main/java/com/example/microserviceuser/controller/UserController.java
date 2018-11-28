package com.example.microserviceuser.controller;

import com.example.microserviceuser.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private static List<User> users = new ArrayList<>(100);

    static Integer maxId = 0;

    static {
        User user0 = new User();
        user0.setUserId(newId());
        user0.setAge(0);
        user0.setEmail("user0@123.com");
        user0.setName("user0");

        User user1 = new User();
        user1.setUserId(newId());
        user1.setAge(10);
        user1.setEmail("user1@123.com");
        user1.setName("user1");

        User user2 = new User();
        user2.setUserId(newId());
        user2.setAge(20);
        user2.setEmail("user2@123.com");
        user2.setName("user2");

        User user3 = new User();
        user3.setUserId(newId());
        user3.setAge(30);
        user3.setEmail("user3@123.com");
        user3.setName("user3");


        users.add(user0);
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    static Integer newId() {
        return maxId++;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
        List<User> result = new ArrayList<>();
        users.forEach(u -> {
            if (u.getUserId().equals(userId)) result.add(u);
        });
        if (result.size() == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get(0));
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer from = Math.max((pageNum - 1) * pageSize, 0);
        Integer to = Math.min(pageNum * pageSize, users.size());
        if(from > to) return ResponseEntity.noContent().build();
        users = users.subList(from, to);
        return ResponseEntity.ok(users);
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setUserId(newId());
        users.add(user);
        return ResponseEntity.created(URI.create("/user/" + user.getUserId())).body(user);
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User newUser) {
        if (!users.contains(newUser)) {
            return ResponseEntity.notFound().build();
        }
        Integer index = users.indexOf(newUser);
        users.set(index, newUser);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId) {
        User fakeUser = new User();
        fakeUser.setUserId(userId);
        if (!users.remove(fakeUser)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
