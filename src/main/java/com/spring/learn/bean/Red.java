package com.spring.learn.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Red implements Serializable, InitializingBean, DisposableBean {
    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("red initial...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("red destroy...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Red{" +
                "name='" + name + '\'' +
                '}';
    }
}
