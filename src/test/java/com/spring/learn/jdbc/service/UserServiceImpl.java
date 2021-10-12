package com.spring.learn.jdbc.service;

import com.spring.learn.jdbc.bean.User;
import com.spring.learn.jdbc.mapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into user(name, age, sex) values (?, ?, ?)",
                new Object[]{user.getName(), user.getAge(), user.getSex()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
        throw new RuntimeException("事务 运行时异常");
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from user", new UserRowMapper());
    }
}
