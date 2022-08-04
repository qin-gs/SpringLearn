package com.spring.learn.lite;

import com.spring.learn.bean.User;
import org.springframework.context.annotation.Bean;

public class UserService {

    @Bean
    public User user() {
        return new User();
    }
}
