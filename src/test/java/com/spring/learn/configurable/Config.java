package com.spring.learn.configurable;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;

@Configuration
@EnableSpringConfigured
@ComponentScan(basePackages = "com.spring.learn.configurable")
public class Config {

}

@Component
class Service {
    public void hello() {
        System.out.println("Hello");
    }
}

@Configurable(preConstruction = true, autowire = Autowire.BY_NAME)
class Controller {
    @Autowired
    private Service service;

    public void hello() {
        service.hello();
    }
}
