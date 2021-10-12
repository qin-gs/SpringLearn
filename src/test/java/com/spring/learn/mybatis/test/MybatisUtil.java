package com.spring.learn.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {
    private final static SqlSessionFactory FACTORY;

    static {
        String resource = "mybatis/mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FACTORY = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return FACTORY;
    }
}
