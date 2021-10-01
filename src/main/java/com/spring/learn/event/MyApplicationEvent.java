package com.spring.learn.event;

import org.springframework.context.ApplicationEvent;

/**
 * 创建自己的事件
 */
public class MyApplicationEvent extends ApplicationEvent {
    private final String content;
    private final String address;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyApplicationEvent(Object source, String address, String content) {
        super(source);
        this.address = address;
        this.content = content;
    }

    public void doSomething() {
        System.err.println("事件发送过来的内容 address: " + this.address + ", content: " + content);
    }
}
