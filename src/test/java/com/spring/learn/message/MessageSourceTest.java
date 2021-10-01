package com.spring.learn.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

@DisplayName("MessageSource 测试")
public class MessageSourceTest {

    @Test
    public void test() {
        MessageSource messageSource = new AnnotationConfigApplicationContext(Config.class);
        String message = messageSource.getMessage("message", null, "default message", Locale.US);
        System.out.println(message);
    }
}
