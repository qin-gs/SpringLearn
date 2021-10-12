package com.spring.learn.inject;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("./xml/inject.xml");
        UserManager bean = context.getBean(UserManager.class);
        System.out.println(bean);
    }
}
