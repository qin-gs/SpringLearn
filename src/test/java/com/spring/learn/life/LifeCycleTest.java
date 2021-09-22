package com.spring.learn.life;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("bean生命周期方法调用顺序测试")
public class LifeCycleTest {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
    }
}