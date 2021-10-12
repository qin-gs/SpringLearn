package com.spring.learn.dao;

import com.spring.learn.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;
    private final SimpleJdbcInsert insertUser;
    @Autowired
    private NamedParameterJdbcTemplate nJdbcTemplate;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource).withTableName("user");
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("user")
                .usingColumns("id", "name", "age"); // 选择插入的列
        // .usingGeneratedKeyColumns("id"); // 指定的列需要数据库自动生成
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void add(User user) {
        Map<String, String> map = Map.of("id", "231412", "name", "bbb", "age", "456");
        // 执行插入，map的key需要和数据库列名一致
        int execute = insertUser.execute(map); // 自动生成sql语句: INSERT INTO user (id, name, age, sex) VALUES(?, ?, ?, ?)

        /*
          两种方式用对象和map配置参数
         */
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        Number number = insertUser.executeAndReturnKey(parameters);

        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", "123");

    }

    public void userMappingQuery(DataSource dataSource) {
        UserMappingQuery query = new UserMappingQuery(dataSource);
        query.findObject(123);
    }

    public void deleteBatch(List<String> ids) {
        // 通过 NamedParameterJdbcTemplate 第二个参数直接给List，会自动生成占位符'?'
        String sql = "delete from user where age = :age and id in (:ids)";
        Map<String, Object> map = Map.of("age", 12, "ids", ids);
        nJdbcTemplate.update(sql, map);
    }
}
