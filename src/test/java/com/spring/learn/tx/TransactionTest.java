package com.spring.learn.tx;

import com.spring.learn.jdbc.bean.User;
import com.spring.learn.jdbc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("xml/tx.xml");
        UserService service = context.getBean(UserService.class);
        User user = new User();
        user.setName("tx");
        user.setAge('t');
        user.setSex("F");
        service.save(user);
    }
}
