package com.udemy.restudemy.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@ComponentScan(basePackages = "security")
@Getter
public class AppProperties {

    private Environment environment;

    @Autowired
    public AppProperties(Environment environment) {
        this.environment = environment;
    }

    public String getTokenSecret()
    {
        return environment.getProperty("tokenSecret");
    }
}
