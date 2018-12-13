package com.example.websecuritypasswordencoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@SpringBootApplication
@Controller
public class WebSecurityPasswordEncoderApplication {

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping("/test01")
    @ResponseBody
    public String getTest01(){
        StringBuilder result = new StringBuilder("hi ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            result.append(authentication.getName() + " ");
            //result.append(authentication.getPrincipal() + " ");
            authentication.getAuthorities().forEach(granted->{
                result.append(granted.getAuthority());
                result.append(" ");
            });
        }
        return result.toString();
    }

    @RequestMapping("/test02")
    @ResponseBody
    public String getTest02(Principal principal){
        return principal.getName();
    }

    @RequestMapping("/test03")
    @ResponseBody
    public String getTest03(Authentication authentication){
        return authentication.getName();
    }

    @RequestMapping("/test04")
    @ResponseBody
    public String getTest04(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

    @RequestMapping("/test05")
    @ResponseBody
    public String getTest07(){
        return this.httpServletRequest.getUserPrincipal().getName();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebSecurityPasswordEncoderApplication.class, args);
    }

}

