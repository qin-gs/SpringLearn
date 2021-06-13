package com.spring.learn.bean;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class Blue {
    private String name;

    @PostConstruct
    public void init() {
        System.out.println("blue init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("blue destroy...");
    }
}
