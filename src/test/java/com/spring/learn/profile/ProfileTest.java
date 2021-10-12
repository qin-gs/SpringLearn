package com.spring.learn.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev") // 可以应用于类 和 方法，可以同时设置多个
public class ProfileTest {
}
