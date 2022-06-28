package com.spring.learn.configurable;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解 @EnableSpringConfigured + @Configurable；让 new 出来的对象也能被注入属性
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Controller controller = new Controller();
        controller.hello();
    }
}
