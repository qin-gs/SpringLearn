package com.spring.learn.event;

import org.springframework.context.ApplicationListener;

public class ListenerTest implements ApplicationListener<EventTest> {
    @Override
    public void onApplicationEvent(EventTest event) {
        event.print();
    }
}
