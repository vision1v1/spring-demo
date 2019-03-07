package com.example.springbootconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"config/db-config.properties"})
public class InfoConfig3 {

    @Value("${info.address}")
    private String address;

    @Value("${info.company}")
    private String company;


    public String getAddress(){
        return address;
    }

    public String getCompany(){
        return company;
    }

}
