package com.example.websecuritypasswordencoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);


    @Bean
    public PasswordEncoder passwordEncoder(){

        //return new BCryptPasswordEncoder(16);

        //return new Pbkdf2PasswordEncoder();

        //return new AspNetIdentityPasswordEncoder();

        //选择算法
        String idForEncode = "pbkdf2";//"bcrypt";
        Map encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("sha256", new StandardPasswordEncoder());
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        return passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder pwdEncoder = passwordEncoder();
        String encodedPwd = pwdEncoder.encode("123");
        LOGGER.info("encodedPwd = {}", encodedPwd);
        auth.inMemoryAuthentication().withUser("user").password(encodedPwd).roles("root");

        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(passwordEncoder().encode("user1@123"))
                .roles("user","admin")
                .and()
                .withUser("user2")
                .password(pwdEncoder.encode("user2@123"))
                .roles("user");
    }
}
