package com.spring.learn.lite;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class LiteTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LiteConfig.class);
        UserService service = context.getBean(UserService.class);
        System.out.println(service);

    }
}
