package com.spring.learn.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@DisplayName("监听事件发布")
public class EventTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        // 发布自定义事件，通常由Service层发布(同时实现ApplicationEventPublisherAware)
        // context.publishEvent(new MyApplicationEvent("自己发布的事件", "123@qq.com", "ccc") {
        // });
        PublisherService service = context.getBean(PublisherService.class);
        service.publishEvent("123@qq.com", "this is a content");

        context.close();
    }
}
