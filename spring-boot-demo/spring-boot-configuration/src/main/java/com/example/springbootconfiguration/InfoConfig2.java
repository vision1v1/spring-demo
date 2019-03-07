package com.example.springbootconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "info")
public class InfoConfig2 {

    private String address;
    private String company;

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getCompany(){
        return company;
    }
}
