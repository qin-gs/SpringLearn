package com.spring.learn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insert;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource).withTableName("user");
    }

    public void add() {
        Map<String, String> map = Map.of("id", "aaa", "name", "bbb");
        // 执行插入，map的key需要和数据库列名一致
        int execute = insert.execute(map);
    }
}
