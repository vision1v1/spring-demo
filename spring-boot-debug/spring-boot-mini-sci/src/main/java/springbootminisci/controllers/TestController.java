package springbootminisci.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getUsers() {
        List<String> result = new ArrayList<>();
        result.add("th000");
        result.add("infi");
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String hi() {
        return "nihao";
    }
}
