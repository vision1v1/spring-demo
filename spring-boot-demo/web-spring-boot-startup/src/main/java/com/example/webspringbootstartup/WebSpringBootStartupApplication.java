package com.example.webspringbootstartup;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.tomcat.util.net.NioEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

@SpringBootApplication
public class WebSpringBootStartupApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSpringBootStartupApplication.class, args);

        //test01();
    }

    public static void test01() {
        String[] args = new String[0];
        ResourceLoader resourceLoader = null;
        SpringApplication app = new SpringApplication(resourceLoader, WebSpringBootStartupApplication.class);
        AnnotationConfigServletWebServerApplicationContext context = (AnnotationConfigServletWebServerApplicationContext) app.run(args);
        WebServer webServer = context.getWebServer();

        //AbstractMessageConverterMethodProcessor

        //DispatcherServlet

        //ContextLoaderListener
        //DefaultServlet
        //NioEndpoint
    }

}
