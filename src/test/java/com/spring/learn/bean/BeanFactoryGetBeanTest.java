package com.spring.learn.bean;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@DisplayName("getBean方法调试")
public class BeanFactoryGetBeanTest {

    private static ApplicationContext context;

    @BeforeAll
    public static void beforeAll() {
        context = new AnnotationConfigApplicationContext(Config.class);
    }

    @Test
    public void getBean() {
        Object blog = context.getBean("com.spring.learn.bean.Blog");
        Object b = context.getBean("blog");
        System.out.println(blog);
    }
}
