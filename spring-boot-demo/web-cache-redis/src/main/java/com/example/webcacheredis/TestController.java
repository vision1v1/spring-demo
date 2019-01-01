package com.example.webcacheredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final String KEY = "Student";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private HashOperations hashOperations;

    @Autowired
    MessagePublisher messagePublisher;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @PostMapping("/set")
    public String setValue(@RequestBody Student student) {
        hashOperations.put(KEY, student.getId().toString(), student);
        return student.getId().toString();
    }

    @GetMapping("/{id}")
    public Student getValue(@PathVariable String id) {
        Student student = (Student) this.hashOperations.get(KEY, id);
        return student;
    }

    @GetMapping("/send")
    public String sendMessage(String content){
        messagePublisher.publish(content);
        return content;
    }
}
