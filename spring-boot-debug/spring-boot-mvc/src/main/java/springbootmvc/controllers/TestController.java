package springbootmvc.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootmvc.exceptions.MyException;
import springbootmvc.response.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
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

    @RequestMapping(value = "hi", method = RequestMethod.GET, consumes = "application/json")
    public String hi() {
        throw new MyException("hi", 1000);
        //return "nihao";
    }

    @GetMapping("/attributes")
    public ResponseEntity<List<String>> getRequestAttributes(HttpServletRequest httpServletRequest) {
        ArrayList<String> list = Collections.list(httpServletRequest.getAttributeNames());
        return ResponseEntity.ok(list);
    }

    //Missing matrix variable 'q' for method parameter of type int]?
    // GET /pets/42;q=11;r=22
    @GetMapping("/pets/{petId}")
    public void findPet(@PathVariable String petId, @MatrixVariable(name = "q",pathVar = "petId") int q) {

        // petId == 42
        // q == 11

        System.out.println("petId:" + petId + " q:" + q);
    }

    @GetMapping("/headers")
    public void testRequestHeader(@RequestHeader("User-Agent") String userAgent,@RequestHeader("Host") String host){
        System.out.println("User-Agent: " + userAgent);
        System.out.println("Host: " + host);
    }

    @GetMapping("/cookie")
    public void testCookie(@CookieValue("JSESSIONID") String cookie){
        System.out.println("JSESSIONID: " + cookie);
    }

    @ExceptionHandler({MyException.class})
    public ResponseEntity<String> handleException(MyException myException) {
        System.out.println(myException);
        return ResponseEntity.ok(myException.toString());
    }

    @GetMapping("/user")
    @JsonView(User.Public.class)
    //@ResponseBody
    public ResponseEntity<User> getUser() {

        //String json =

        User user = new User(1,"th000", "th000-ownerName");
        return ResponseEntity.ok(user);
    }

    @GetMapping("exception/{isRise}")
    @ResponseBody
    public boolean riseException(@PathVariable("isRise") boolean isRise){
        if(isRise){
            throw new RuntimeException("error");
        }
        return isRise;
    }


}
