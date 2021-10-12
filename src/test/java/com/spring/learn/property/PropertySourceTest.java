package com.spring.learn.property;

import com.spring.learn.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/com/spring/learn/property/config.properties")
public class PropertySourceTest {

    @Autowired
    private Environment env;

    @Bean
    public Book getBook() {
        // 指定属性不存在时给一个默认值，或必须存在
        return new Book(env.getRequiredProperty("title"),
                env.getProperty("price", int.class, 20));
    }

    /**
     * 用来解析占位符{@code @Value}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
