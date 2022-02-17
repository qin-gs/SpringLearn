package com.spring.learn.ignore;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IgnoreTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/ignore.xml");
        ListHolder bean = context.getBean(ListHolder.class);
        System.out.println(bean.getList());
    }
}
