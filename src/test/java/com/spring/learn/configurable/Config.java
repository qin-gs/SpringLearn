package com.spring.learn.configurable;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;

@Configuration
@EnableSpringConfigured
@EnableLoadTimeWeaving
@ComponentScan(basePackages = "com.spring.learn.configurable")
public class Config {

}

@Component
class Service {
    public void hello() {
        System.out.println("Hello");
    }
}

/**
 * preConstruction = true 表示依赖的Bean在构造函数调用之前就被注入了。
 * autowire= Autowire.BY_NAME 表示依赖的 Bean 是按名字来自动装配，也可以使用 Autowire.BY_TYPE，按类型来装配。
 */
@Configurable(preConstruction = true, autowire = Autowire.BY_NAME)
class Controller {
    @Autowired
    private Service service;

    public void hello() {
        service.hello();
    }
}
