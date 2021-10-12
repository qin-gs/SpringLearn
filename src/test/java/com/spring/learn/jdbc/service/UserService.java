package com.spring.learn.jdbc.service;

import com.spring.learn.jdbc.bean.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public interface UserService {
    void save(User user);

    List<User> getUsers();
}
