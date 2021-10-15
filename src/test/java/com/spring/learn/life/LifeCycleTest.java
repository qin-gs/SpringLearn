package com.spring.learn.life;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@DisplayName("bean生命周期方法调用顺序测试")
public class LifeCycleTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        BeanInit bean = context.getBean(BeanInit.class);
        context.close();
    }

    @Test
    public void testXml() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/life.xml");
        BeanInit bean = context.getBean(BeanInit.class);
        context.close();
    }
}