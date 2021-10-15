package com.spring.learn.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MyBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, "beanInit")) {
            System.out.println("5. BeanPostProcessor postProcessBeforeInitialization");
        }
        return DestructionAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, "beanInit")) {
            System.out.println("10. BeanPostProcessor postProcessAfterInitialization");
        }
        return DestructionAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, "beanInit")) {
            System.out.println("12. DestructionAwareBeanPostProcessor postProcessBeforeDestruction");
        }
    }
}
