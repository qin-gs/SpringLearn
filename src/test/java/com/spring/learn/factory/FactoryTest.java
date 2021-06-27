package com.spring.learn.factory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    }
}
