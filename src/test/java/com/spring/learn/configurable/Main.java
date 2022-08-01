package com.spring.learn.configurable;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解 @EnableSpringConfigured + @EnableLoadTimeWeaving + @Configurable；让 new 出来的对象也能被注入属性；
 * 启动时需要添加虚拟机参数 -javaagent:spring-instrument-5.3.7.jar
 * <p>
 * aspectj-maven-plugin 只能使用 jdk8，所以看不到效果
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        // 看不到效果
        Controller controller = new Controller();
        controller.hello();
    }
}
