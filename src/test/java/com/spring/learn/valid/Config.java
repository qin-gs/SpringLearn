package com.spring.learn.valid;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class Config {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:/properties/valid.properties");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }
}
