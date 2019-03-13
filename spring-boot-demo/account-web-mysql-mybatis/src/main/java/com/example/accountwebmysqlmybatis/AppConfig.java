package com.example.accountwebmysqlmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.example.accountwebmysqlmybatis")
//1.用@MapperScan扫描进去。
//2.或者在仓储类上，打上 @Mapper
@MapperScan("com.example.accountwebmysqlmybatis.repository")
public class AppConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("Admin@123");
        driverManagerDataSource.setUrl("jdbc:mysql://192.168.209.131:3306/Account?useSSL=false");
        return driverManagerDataSource;
    }
}
