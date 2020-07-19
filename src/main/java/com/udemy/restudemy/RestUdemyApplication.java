package com.udemy.restudemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class RestUdemyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RestUdemyApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RestUdemyApplication.class);
    }
}
