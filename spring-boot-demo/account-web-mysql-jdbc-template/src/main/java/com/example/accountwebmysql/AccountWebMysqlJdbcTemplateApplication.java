package com.example.accountwebmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class AccountWebMysqlJdbcTemplateApplication {


    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.209.131:3306/Account?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("Admin@123");
        return dataSource;
    }


    public static void main(String[] args) {
        SpringApplication.run(AccountWebMysqlJdbcTemplateApplication.class, args);
    }
}
