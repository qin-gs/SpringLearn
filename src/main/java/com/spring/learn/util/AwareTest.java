package com.spring.learn.util;

import com.spring.learn.bean.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

public class AwareTest implements BeanFactoryAware, InitializingBean {

    private String name = "a";
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void testAware() {
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.name = "afterPropertiesSet";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
