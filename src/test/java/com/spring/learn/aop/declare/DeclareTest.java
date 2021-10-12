package com.spring.learn.aop.declare;

import com.spring.learn.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeclareTest {
    @Test
    public void test() {

        User user = new User("qqq");

        ApplicationContext context = new ClassPathXmlApplicationContext("xml/declare.xml");
        User bean = context.getBean(User.class);
        System.out.println(bean);
        Verifier b = (Verifier) bean;
        System.out.println(b.validate(user));
    }
}
