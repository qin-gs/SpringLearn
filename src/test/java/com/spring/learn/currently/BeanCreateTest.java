package com.spring.learn.currently;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCreateTest {

    @Test
    public void testCircleByConstructor() {
        new ClassPathXmlApplicationContext("xml/bean-currently.xml");
    }
}
