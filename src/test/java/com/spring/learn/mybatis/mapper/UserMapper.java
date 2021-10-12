package com.spring.learn.mybatis.mapper;

import com.spring.learn.mybatis.bean.User;

public interface UserMapper {
    void insertUser(User user);

    User getUser(int id);
}
