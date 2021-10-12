package com.spring.learn.el;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ElMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);
        User bean = context.getBean(User.class);
        System.out.println(bean);
    }
}
