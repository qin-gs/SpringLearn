package com.spring.learn.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Blue{" +
                "name='" + name + '\'' +
                '}';
    }
}
