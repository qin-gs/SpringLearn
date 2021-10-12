package com.spring.learn.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ElService {

    @Bean
    public Book book() {
        return new Book("a book", 12.3F);
    }

    /**
     * el表达式获取对象中的值
     */
    @Bean
    public User user(@Value("#{book.price}") float price) {
        System.out.println(price);
        return new User();
    }

}
