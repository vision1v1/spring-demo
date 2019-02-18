package com.example.webspringbootstartup;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HelloRestController {

    @GetMapping("/hi")
    public String getHi(){
        return "Hello Rest Controller";
    }

}
