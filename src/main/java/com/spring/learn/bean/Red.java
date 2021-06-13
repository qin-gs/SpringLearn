package com.spring.learn.bean;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Red implements Serializable, InitializingBean, DisposableBean {
    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("red initial...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("red destroy...");
    }
}
