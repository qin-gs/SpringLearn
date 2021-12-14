package com.spring.learn.callback;

import com.alibaba.druid.pool.DruidDataSource;
import com.spring.learn.bean.Blog;
import com.spring.learn.jdbc.bean.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@DisplayName("jdbcTemplate 查询的三种回调")
public class QueryTest {

    @Autowired
    private static JdbcTemplate jdbcTemplate;
    @Autowired
    private static NamedParameterJdbcTemplate namedJdbcTemplate;

    @BeforeAll
    public static void beforeAll() {
        DataSource dataSource = dataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @AfterAll
    public static void afterAll() {
        // ...
    }

    @Test
    public void test() {
        String sql = "select * from blog";

        // 需要自行处理所有的 ResultSet 结果集
        Blog blog = jdbcTemplate.query(sql, new ResultSetExtractor<Blog>() {
            @Override
            public Blog extractData(ResultSet rs) throws SQLException, DataAccessException {
                // 如果只有一条数据，要先调用 next 方法
                // 如果有多条数据，需要 while(rs.next()) 循环调用
                if (rs.next()) {
                    Blog blog = new Blog();
                    blog.setId(rs.getString("id"));
                    // ...
                    return blog;
                }
                return null;
            }
        });

        // 仅关注单行结果集的处理，没有返回值
        // 可以选择是否保存结果集
        Blog b = new Blog();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                b.setId(rs.getString("id"));
                // ...
            }
        });

        // 只关注单行结果的处理
        // ResultSetExtractor 的精简版，直接返回 List
        List<Blog> blogList = jdbcTemplate.query(sql, new RowMapper<Blog>() {
            @Override
            public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
                Blog b = new Blog();
                b.setId(rs.getString("id"));
                // ...
                return b;
            }
        });
    }

    @Test
    public void bean() {

        // 可以用 MapSqlParameterSource 或 Map 传参
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("id", "1");
        Map<String, String> map = Map.of("id", "1"); // SqlParameterSource
        Integer integer = namedJdbcTemplate.queryForObject("select count(*) from blog where id = :id", map, Integer.class);

        // 将对象作为参数传进去 属性名会被作为参数名
        Blog blog = new Blog();
        blog.setId("blog-1");
        blog.setTitle("博客1");
        BeanPropertySqlParameterSource beanSource = new BeanPropertySqlParameterSource(blog);
        String sql = "select count(*) from blog where id = :id and title = :title";
        Integer i = namedJdbcTemplate.queryForObject(sql, beanSource, Integer.class);

        // 直接使用 bean 对象 更新/插入
        namedJdbcTemplate.update("insert into blog(id, title, body) values (:id, :title, :body)", beanSource);
        namedJdbcTemplate.update("update blog set title = :title, body = :body where id = :id", beanSource);
    }

    @Test
    public void t() throws Exception {
        User user = new User(12, "www", 123);
        // Map<String, Object> map = Map.of("id", 12, "name", "www", "age", 123);
        // BeanUtils.populate(user, map);
        BeanPropertySqlParameterSource beanSource = new BeanPropertySqlParameterSource(user);

        beanSource.getValue("id");

        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        // 直接使用 bean 对象 更新/插入
        int update = namedJdbcTemplate.update("insert into user(id, name, age) values (:id, :name, :age)", beanSource);
        int u = namedJdbcTemplate.update("update user set name = :name, age = :age where id = :id", beanSource);

        System.out.println(update);
        System.out.println(u);

    }

    public static DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
