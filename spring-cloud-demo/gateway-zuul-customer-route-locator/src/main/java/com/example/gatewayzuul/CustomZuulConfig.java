package com.example.gatewayzuul;

import com.example.gatewayzuul.PreFilter.RouterFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomZuulConfig {

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    ServerProperties serverProperties;

    @Bean
    CustomRouteLocator routeLocator() {
        CustomRouteLocator locator = new CustomRouteLocator(serverProperties.getServlet().getContextPath(), zuulProperties);
        return locator;
    }

    @Bean
    RouterFilter routerFilter(){
        return new RouterFilter();
    }
}
