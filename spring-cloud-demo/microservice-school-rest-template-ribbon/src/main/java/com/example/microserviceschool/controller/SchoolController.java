package com.example.microserviceschool.controller;

import com.example.microserviceschool.sao.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient2;

    @RequestMapping("/students")
    public ResponseEntity<List<User>> getStudents() {
        final ResponseEntity<User[]> result = restTemplate.getForEntity("http://MICROSERVICE-USER-PROVIDER/user/list", User[].class);
        //final ResponseEntity<User[]> result = restTemplate.getForEntity("http://localhost:9000/user/list", User[].class);
        //User[] users = restTemplate.getForObject("http://MICROSERVICE-USER-PROVIDER/user/list",User[].class);
        User[] users = result.getBody();
        return ResponseEntity.ok(Arrays.asList(users));
    }

    @RequestMapping("/test")
    public String test() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("MICROSERVICE-USER-PROVIDER");
        LOGGER.info(" xxxxxxxxx serviceInstance : {} Ip : {} Port {}", serviceInstance.getInstanceId(), serviceInstance.getHost(), serviceInstance.getPort());

        ServiceInstance serviceInstance2 = this.loadBalancerClient2.choose("MICROSERVICE-USER-PROVIDER-01");
        LOGGER.info(" ooooooooo serviceInstance : {} Ip : {} Port {}", serviceInstance2.getInstanceId(), serviceInstance2.getHost(), serviceInstance2.getPort());

        return "test ribbon Balancer Client";
    }


}
