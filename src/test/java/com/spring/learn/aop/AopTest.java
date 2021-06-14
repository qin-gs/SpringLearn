package com.spring.learn.aop;

import com.spring.learn.config.Config;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    @Test
    public void test() {
        AopMethod method = context.getBean(AopMethod.class);
        method.testAop(1, 2);
    }
}
