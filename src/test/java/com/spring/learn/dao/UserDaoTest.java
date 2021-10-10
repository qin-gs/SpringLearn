package com.spring.learn.dao;

import com.spring.learn.bean.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    static ApplicationContext context;

    @BeforeAll
    public static void beforeAll() {
        context = new AnnotationConfigApplicationContext(Config.class);
    }

    @Test
    void add() {
        UserDao bean = context.getBean(UserDao.class);
        bean.add(new User());
    }

    @Test
    void deleteBatch() {
        UserDao dao = context.getBean(UserDao.class);
        dao.deleteBatch(Arrays.asList("1", "2", "3"));
    }
}