package com.spring.learn.aop;

import com.spring.learn.config.Config;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AopMethod method = context.getBean(AopMethod.class);
        System.out.println(method.testAop(1, 2));
    }

    // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    //
    // @Test
    // public void test() {
    //     AopMethod method = context.getBean(AopMethod.class);
    //     System.out.println(method.testAop(1, 2));
    // }
}
