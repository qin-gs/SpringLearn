package com.spring.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 1. EnableTransactionManagement 开启基于注解的事务管理功能
 * 2. 配置事务管理器
 */
@Configuration
@EnableTransactionManagement
public class TxConfig {

    // @Bean
    // public DataSource dataSource() throws PropertyVetoException {
    //     ComboPooledDataSource dataSource = new ComboPooledDataSource();
    //     dataSource.setUser("root");
    //     dataSource.setPassword("root");
    //     dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mybatis_learn?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT-8");
    //     dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
    //     return dataSource;
    // }

    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) throws PropertyVetoException {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(@Autowired DataSource dataSource) throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource);
    }
}
