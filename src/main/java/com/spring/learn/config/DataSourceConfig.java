package com.spring.learn.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.spring.learn.bean.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource("/dbconfig.properties")
public class DataSourceConfig implements EmbeddedValueResolverAware {

    @Value("${jdbc.username}")
    private String username;

    private String driverClass;

    @Bean
    @Profile("default")
    public Color testColor() {
        return new Color();
    }

    /**
     * Profile
     * 使用命令行参数设置环境表示 -Dspring.profiles.active=dev
     * 使用代码 context.getEnvironment().setActiveProfiles("dev")
     *
     * 也可以修饰类
     */
    @Bean
    @Profile("default")
    public DataSource dataSource(@Value("${jdbc.password}") String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mybatis_learn?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT-8");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Bean
    @Profile("prod")
    public DataSource dataSourceProd(@Value("${jdbc.password}") String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mybatis_learn?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT-8");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Bean
    @Profile("dev")
    public DataSource dataSourceDev(@Value("${jdbc.password}") String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mybatis_learn?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT-8");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.driverClass = resolver.resolveStringValue("${jdbc.driverClass}");
    }
}
