package com.spring.learn.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("xml/aop.xml");
        TestBean bean = context.getBean(TestBean.class);
        bean.testAop();
    }

}
