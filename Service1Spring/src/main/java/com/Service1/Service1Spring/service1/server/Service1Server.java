package com.Service1.Service1Spring.service1.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Service1Server implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Service1Server.class, args);
    }
}
