package com.example.microserviceschool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @RequestMapping("/students")
    public ResponseEntity<List<String>> getStudents(){
        List<String> studentNameList = new ArrayList<>();
        return ResponseEntity.ok(studentNameList);
    }

}
