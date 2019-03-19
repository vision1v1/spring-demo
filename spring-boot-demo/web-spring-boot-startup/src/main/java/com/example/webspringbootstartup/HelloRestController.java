package com.example.webspringbootstartup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class HelloRestController {

    @GetMapping("/hi")
    public String getHi(){
        return "Hello Rest Controller";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getUsers() {
        List<String> result = new ArrayList<>();
        result.add("th000");
        result.add("infi");
        return ResponseEntity.ok(result);
    }

}
