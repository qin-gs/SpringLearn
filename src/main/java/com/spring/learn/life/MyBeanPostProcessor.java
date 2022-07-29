package com.spring.learn.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MyBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, "beanInit")) {
            System.out.println("14. DestructionAwareBeanPostProcessor postProcessBeforeDestruction");
        }
    }
}
