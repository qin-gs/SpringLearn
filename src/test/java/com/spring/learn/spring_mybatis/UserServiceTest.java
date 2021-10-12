package com.spring.learn.spring_mybatis;

import com.spring.learn.mybatis.bean.User;
import com.spring.learn.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-mybatis.xml");
        UserMapper mapper = (UserMapper) context.getBean("userMapper");
        User user = mapper.getUser(1);
        System.out.println("user = " + user);
    }
}
