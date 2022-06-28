package com.spring.learn.mybatis.test;

import com.spring.learn.mybatis.bean.User;
import com.spring.learn.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan()
@DisplayName("mybatis 单独测试")
public class MapperTest {

    static SqlSessionFactory factory = null;

    static {
        factory = MybatisUtil.getSqlSessionFactory();
    }

    @DisplayName("mybatis 测试 增加")
    @Order(1)
    @Test
    public void addTest() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User("www", 5);
        mapper.insertUser(user);
        sqlSession.commit(); // 必须手动提交
        sqlSession.close();
    }

    @DisplayName("mybatis 测试 查询")
    @Order(2)
    @Test
    public void getTest() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUser(3);
        System.out.println("user = " + user);
        sqlSession.close();
    }
}
