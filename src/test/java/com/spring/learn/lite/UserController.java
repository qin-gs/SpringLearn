package com.spring.learn.lite;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    @Bean
    public UserService userService() {
        return new UserService();
    }
}
