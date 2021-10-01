package com.spring.learn.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 发布自己的事件
 */
public class PublisherService implements ApplicationEventPublisherAware {
    private Set<String> blackList;
    private ApplicationEventPublisher publisher;

    public PublisherService(Set<String> blackList) {
        this.blackList = blackList;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 发布事件, 通知所有的事件监听器
     */
    public void publishEvent(String address, String content) {
        if (blackList.contains(address)) {
            this.publisher.publishEvent(new MyApplicationEvent(new Object(), address, content));
            System.out.println(address + " " + content);
        } else {
            // 进行下一步逻辑
        }
    }
}
