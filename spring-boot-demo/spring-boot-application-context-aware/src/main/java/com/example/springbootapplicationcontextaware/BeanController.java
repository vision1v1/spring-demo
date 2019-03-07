package com.example.springbootapplicationcontextaware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("bean")
public class BeanController {

    @Autowired
    private BeanProvider beanProvider;

    @Autowired
    ApplicationContext context;

    @RequestMapping(path = "/names",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllBeanNames(){
        return ResponseEntity.ok(beanProvider.getAllBeanNames());
    }


    @GetMapping(path = "/controllers")
    public ResponseEntity<List<String>> getAllControllersBeanNames(){
        return ResponseEntity.ok(beanProvider.getAllControllerBeanNames());
    }

    @RequestMapping(path = "/v2/names",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllBeanNamesV2(){
        return ResponseEntity.ok(Arrays.stream(context.getBeanDefinitionNames()).sorted().collect(Collectors.toList()));
    }

}
