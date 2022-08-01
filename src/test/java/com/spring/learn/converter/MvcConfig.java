package com.spring.learn.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 在 controller 进行参数映射的时候使用；
     * DefaultConversionService 中已经添加了很多内置的
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToInteger());
        registry.addConverterFactory(new StringToEnumConverterFactory());
        registry.addConverter(new UserGenericConverter());
        registry.addConverter(new RequestHeaderDecodeConverter("utf-8"));
    }
}
