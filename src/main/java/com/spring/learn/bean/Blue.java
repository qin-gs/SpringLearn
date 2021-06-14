package com.spring.learn.bean;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class Blue {
    private String name;

    /**
     * jdk11 中已被启用，需要引入jar包 javax.annotation-api
     */
    @PostConstruct
    public void init() {
        System.out.println("blue init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("blue destroy...");
    }
}
