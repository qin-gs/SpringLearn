package com.spring.learn.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        // System.out.println(context.getBean(User.class));

        // BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring-config.xml"));
        BeanFactory bf = new ClassPathXmlApplicationContext("xml/spring-config.xml");
        User bean = (User) bf.getBean("user");
        System.out.println(bean);

    }
}
