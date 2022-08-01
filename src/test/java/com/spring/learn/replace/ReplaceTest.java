package com.spring.learn.replace;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 可以直接用 aop 代替
 */
public class ReplaceTest {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("./xml/replace.xml");
        TestChangeMethod test = (TestChangeMethod) bf.getBean("testChangeMethod");
        test.changeMe();
    }
}
