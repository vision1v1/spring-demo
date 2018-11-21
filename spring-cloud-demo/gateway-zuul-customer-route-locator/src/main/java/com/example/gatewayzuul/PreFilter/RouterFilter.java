package com.example.gatewayzuul.PreFilter;

import com.example.gatewayzuul.CustomRouteLocator;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;

import java.util.List;

public class RouterFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouterFilter.class);

    @Autowired
    private CustomRouteLocator locator;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        LOGGER.info("hashCode {}",locator.hashCode());
        List<Route> routes = locator.getRoutes();
        routes.forEach(route -> {
            LOGGER.info("++++++++++{}+++++++++++",route.toString());
        });
        return null;
    }
}
