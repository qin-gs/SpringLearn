package com.spring.learn.bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Data
public class Person implements ApplicationContextAware {

    private ApplicationContext context;

    private String name;
    private int age;

    public Person() {
    }

    public void init() {
        System.out.println("person init...");
    }

    public void destroy() {
        System.out.println("person destroy...");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
