package com.example.microserviceschool.feign;

import com.example.config.UserClientFeignConfiguration;
import com.example.microserviceschool.sao.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "microservice-user-provider",configuration = UserClientFeignConfiguration.class)
public interface UserClient {

    //@RequestMapping(method = RequestMethod.GET, value = "/user/{userId}", consumes = "application/json")
    @RequestLine("GET /user/{userId}")
    User getUserById(@Param("userId") Integer userId);

//    @GetMapping(value = "/user/list", consumes = "application/json")
//    ResponseEntity<List<User>> getUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize);
//
//    @PostMapping(value = "/user/", consumes = "application/json")
//    ResponseEntity<User> createUser(@RequestBody User user);
//
//    @PutMapping(value = "/user/", consumes = "application/json")
//    ResponseEntity<User> updateUser(@RequestBody User newUser);
//
//    @DeleteMapping(value = "/user/{userId}", consumes = "application/json")
//    ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId);
}
