package com.spring.learn.event;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    /**
     * 使用注解接收事件
     */
    @EventListener(classes = {ContextClosedEvent.class})
    public void listen(ContextClosedEvent event) {
        System.out.println("service 监听事件" + event);
    }
}
