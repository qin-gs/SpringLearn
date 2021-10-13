package com.spring.learn.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public Blog blog() {
		Blog blog = new Blog();
		blog.setId("a id");
		blog.setTitle("a title");
		return blog;
	}
}
