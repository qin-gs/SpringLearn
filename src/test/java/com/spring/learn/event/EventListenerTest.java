package com.spring.learn.event;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventListenerTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:xml/event.xml");
        EventTest event = new EventTest("hello", "msg");
        context.publishEvent(event);
    }
}
