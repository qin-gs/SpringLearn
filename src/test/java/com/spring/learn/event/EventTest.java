package com.spring.learn.event;

import org.springframework.context.ApplicationEvent;

public class EventTest extends ApplicationEvent {
    private static final long serialVersionUID = 2715426747981831875L;

    public String msg;

    public EventTest(Object source) {
        super(source);
    }

    public EventTest(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void print() {
        System.out.println(msg);
    }
}
