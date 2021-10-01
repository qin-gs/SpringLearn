package com.spring.learn.message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Arrays;

@Configuration
public class Config {

    /**
     * 如果是中文环境，会寻找 format_zh.properties 这个文件
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        String[] file = {"format", "exceptions", "windows"};
        messageSource.setBasenames(Arrays.stream(file)
                .map(x -> "classpath:properties/" + x)
                .toArray(String[]::new));
        return messageSource;
    }
}
