package com.spring.learn.jdbc.test;

import com.spring.learn.jdbc.bean.User;
import com.spring.learn.jdbc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringJdbcTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("xml/jdbc.xml");
        UserService service = context.getBean(UserService.class);
        User user = new User();
        user.setName("qqq");
        user.setAge(20);
        user.setSex("M");

        service.save(user);

        List<User> users = service.getUsers();
        users.forEach(System.out::println);
    }
}
