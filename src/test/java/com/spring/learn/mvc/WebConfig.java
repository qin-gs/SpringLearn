package com.spring.learn.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 添加自定义格式器转换器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
    }
}
