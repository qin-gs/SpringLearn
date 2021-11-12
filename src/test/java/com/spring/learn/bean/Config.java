package com.spring.learn.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 用 nameGenerator 将扫描到的对象使用自己的命名策略
 * 但是没有扫描到的，配置文件中使用 @Bean 声明的对象还是原来的命名
 */
@Configuration
@ComponentScan(basePackages = "com.spring.learn.bean", nameGenerator = MyBeanNameGenerator.class)
public class Config {

    @Bean
    public Blog blog() {
        Blog blog = new Blog();
        blog.setId("a id");
        blog.setTitle("a title");
        return blog;
    }
}
