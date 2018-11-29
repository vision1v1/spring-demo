package com.example.microserviceschool.controller;

import com.example.microserviceschool.sao.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
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
        //eurekaClient 集成了 Ribbon 默认负载策略，轮询调用该服务的所有实例
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("MICROSERVICE-USER-PROVIDER", false);
        String result = instance.getHomePageUrl() + " " + instance.getIPAddr() + ":" + instance.getPort();
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/testhystrix")
    @HystrixCommand(fallbackMethod = "testHystrixFallback")
    public String testHystrix(){
        throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE);
        //return "hystrix";
    }

    private String testHystrixFallback(){
        return "hi I'm testHystrix I Circuit Break";
    }

}
