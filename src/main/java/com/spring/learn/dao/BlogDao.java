package com.spring.learn.dao;

import com.spring.learn.bean.Blog;
import com.spring.learn.exception.CustomErrorCodeTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.InterruptibleBatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class BlogDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public BlogDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // 自定义处理一下数据库返回的错误状态码
        CustomErrorCodeTranslator translator = new CustomErrorCodeTranslator();
        translator.setDataSource(dataSource);
        this.jdbcTemplate.setExceptionTranslator(translator);

        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void test() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from blog", Integer.class);

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

        // 从NamedParameterJdbcTemplate 中获取原生的 jdbcTemplate 执行语句
        JdbcOperations jdbcOperations = namedJdbcTemplate.getJdbcOperations();
    }

    public void batchTest(List<Blog> blogs) {
        // 批量执行
        String sql = "update blog set title = ? where id = ?";
        this.jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, blogs.get(i).getTitle());
                        ps.setString(2, blogs.get(i).getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return blogs.size();
                    }
                });

        new InterruptibleBatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {

            }

            @Override
            public int getBatchSize() {
                return 0;
            }

            /**
             * 在数量不确定的情况下，批处理结束的标识
             */
            @Override
            public boolean isBatchExhausted(int i) {
                return false;
            }
        };

        // 具有列表对象的批处理操作，返回结果为每个批处理条目影响的行数
        int[] ints = this.namedJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(Arrays.asList(blogs.toArray())));
        int[] ints1 = this.jdbcTemplate.batchUpdate(sql,
                List.of(new Object[]{blogs.get(0).getTitle(), blogs.get(0).getId()},
                        new Object[]{blogs.get(1).getTitle(), blogs.get(1).getId()}));

        // 具有多个批次的批处理操作
        this.jdbcTemplate.batchUpdate(
                sql,
                blogs,
                100,
                (ps, argument) -> {
                    ps.setString(1, argument.getTitle());
                    ps.setString(2, argument.getId());
                });
    }
}
