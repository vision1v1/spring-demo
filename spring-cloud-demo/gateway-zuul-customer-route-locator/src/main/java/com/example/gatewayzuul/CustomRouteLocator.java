package com.example.gatewayzuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRouteLocator.class);

    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
    }

    @Override
    public void refresh() {
        super.doRefresh();
    }

    @Override
    public Route getMatchingRoute(final String path) {
        LOGGER.info("path : {}", path);
        Route route = super.getMatchingRoute(path);
        LOGGER.info("route : {}", route.toString());
        return route;
    }


    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LOGGER.info("{}", "-----------------------");
        Map<String, ZuulProperties.ZuulRoute> routeMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        //routeMap.putAll(super.locateRoutes());
        routeMap.putAll(loadCustomRoute());
        return routeMap;
    }


    private Map<String, ZuulProperties.ZuulRoute> loadCustomRoute() {
        Map<String, ZuulProperties.ZuulRoute> routeMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        ZuulProperties.ZuulRoute route1 = new ZuulProperties.ZuulRoute();
        route1.setId("route1");
        route1.setPath("/thirdparty/**");
        route1.setUrl("http://localhost:9000");
        //route1.setServiceId("MICROSERVICE-USER-WITH-EUREKA");
        //route1.setStripPrefix(true);

        ZuulProperties.ZuulRoute route2 = new ZuulProperties.ZuulRoute();
        route2.setId("route2");
        route2.setPath("/thirdparty2/**");
        //route2.setUrl("http://localhost:9000");
        route2.setServiceId("MICROSERVICE-USER-WITH-EUREKA");
        //route2.setStripPrefix(true);


        routeMap.put(route1.getPath(), route1);
        routeMap.put(route2.getPath(), route2);
        return routeMap;
    }
}
