package com.example.microserviceschool.controller;

import com.example.microserviceschool.sao.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping("/students")
    public ResponseEntity<List<User>> getStudents() {
        final ResponseEntity<User[]> result = restTemplate.getForEntity("http://MICROSERVICE-USER-PROVIDER/user/list", User[].class);
        //final ResponseEntity<User[]> result = restTemplate.getForEntity("http://localhost:9000/user/list", User[].class);
        //User[] users = restTemplate.getForObject("http://MICROSERVICE-USER-PROVIDER/user/list",User[].class);
        User[] users = result.getBody();
        return ResponseEntity.ok(Arrays.asList(users));
    }

    @RequestMapping("/test")
    public ResponseEntity<String> test() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("MICROSERVICE-USER-PROVIDER", false);
        String result = instance.getHomePageUrl() + " " + instance.getIPAddr() + ":" + instance.getPort();
        return ResponseEntity.ok(result);
    }


}
