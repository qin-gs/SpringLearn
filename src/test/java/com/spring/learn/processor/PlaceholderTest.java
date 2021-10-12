package com.spring.learn.processor;

import com.spring.learn.bean.Message;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PlaceholderTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("./xml/placeholder.xml");
        Message bean = context.getBean(Message.class);
        System.out.println(bean);
    }
}
