package com.spring.learn.life;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.spring.learn.life")
public class LifeCycleConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy_")
    public BeanInit beanInit() {
        return new BeanInit();
    }
}
