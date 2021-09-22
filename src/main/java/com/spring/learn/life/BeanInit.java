package com.spring.learn.life;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean生命周期的顺序如下
 */
@Component
public class BeanInit implements InitializingBean, DisposableBean {

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    /**
     * <bean init-method="init"></bean>
     */
    public void init() {
        System.out.println("init");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    /**
     * <bean destroy-method="destroy_"></bean>
     */
    public void destroy_() {
        System.out.println("destroy_");
    }

}
