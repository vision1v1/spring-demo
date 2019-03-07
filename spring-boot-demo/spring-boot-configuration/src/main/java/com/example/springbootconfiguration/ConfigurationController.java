package com.example.springbootconfiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("config")
public class ConfigurationController {


    @Autowired
    InfoConfig1 config1;

    @Autowired
    InfoConfig2 config2;

    @Autowired
    InfoConfig3 config3;

    @Autowired
    Environment environment;


    //sping 读取配置的4种方式r
    @GetMapping("/list")
    public ResponseEntity<Map<String, String>> getAllConfig() {
        Map<String, String> result = new ConcurrentHashMap<>();
        result.put("result1", config1.getCompany() + ":" + config1.getAddress());
        result.put("result2", config2.getCompany() + ":" + config2.getAddress());
        result.put("result3", config3.getCompany() + ":" + config3.getAddress());
        result.put("result4", environment.getProperty("info.company") + ":" + environment.getProperty("info.address"));
        return ResponseEntity.ok(result);
    }


}
