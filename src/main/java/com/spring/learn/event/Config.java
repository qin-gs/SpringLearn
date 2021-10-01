package com.spring.learn.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ComponentScan("com.spring.learn.event")
public class Config {

    @Bean
    public PublisherService publisherService() {
        return new PublisherService(Set.of("123@qq.com", "456@qq.com"));
    }
}
