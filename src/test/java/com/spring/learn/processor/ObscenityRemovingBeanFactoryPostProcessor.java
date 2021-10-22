package com.spring.learn.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用该接口来消除一些属性
 */
public class ObscenityRemovingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private Set<String> obscenties;

    public ObscenityRemovingBeanFactoryPostProcessor() {
        this.obscenties = new HashSet<>();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            // 创建一个StringValueResolver(是函数式接口，可以用方法引用)
            StringValueResolver resolver = this::resolveStringValue;
            BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(resolver);
            visitor.visitBeanDefinition(beanDefinition);
        }
    }

    private boolean isObscene(Object value) {
        return this.obscenties.contains(value.toString().toUpperCase());
    }

    public void setObscenties(Set<String> obscenties) {
        this.obscenties.clear();
        this.obscenties.addAll(obscenties.stream().map(String::toUpperCase).collect(Collectors.toList()));
    }

    private String resolveStringValue(String strVal) {
        return isObscene(strVal) ? "******" : strVal;
    }
}
