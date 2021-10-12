package com.spring.learn;

import com.spring.learn.bean.Person;
import com.spring.learn.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {
        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/xml/beans.xml");
        // Object person = context.getBean("person");
        // System.out.println(person);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Person person = context.getBean(Person.class);
        String[] names = context.getBeanNamesForType(Person.class);
        System.out.println(person);
        System.out.println(Arrays.toString(names));
    }
}
