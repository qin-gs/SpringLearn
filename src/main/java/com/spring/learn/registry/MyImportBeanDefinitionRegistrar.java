package com.spring.learn.registry;

import com.spring.learn.bean.User2;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (registry.containsBeanDefinition("com.spring.learn.bean.Comment")) {
            registry.registerBeanDefinition("user", new RootBeanDefinition(User2.class));
        }
    }
}
