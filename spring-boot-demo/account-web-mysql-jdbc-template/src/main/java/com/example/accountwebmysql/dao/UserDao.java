package com.example.accountwebmysql.dao;

import com.example.accountwebmysql.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {

    @Autowired
    private DataSource dataSource;

    private String queryString = "select * from Users where UserId = :userId";

    public User getUserById(String userId) {

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        Map params = new HashMap();

        params.put("userId", userId);

        User user = template.queryForObject(queryString, params, new User());

        return user;
    }
}
