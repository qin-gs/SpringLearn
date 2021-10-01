package com.spring.learn.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 接收自定义事件
 */
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.err.println("接收自定义事件: " + event.toString());
        event.doSomething();
    }
}
