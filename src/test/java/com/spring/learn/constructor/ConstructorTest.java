package com.spring.learn.constructor;

import com.spring.learn.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorTest {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("./xml/constructor.xml");
        // GetBeanTest test = (GetBeanTest) bf.getBean("getBeanTest");
        User bean = (User) bf.getBean("user");
        System.out.println(bean);

        User bean2 = (User) bf.getBean("user2");
        System.out.println(bean2);
    }
}
