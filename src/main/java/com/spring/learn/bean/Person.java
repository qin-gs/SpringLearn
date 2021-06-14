package com.spring.learn.bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * 通过一系列aware接口将spring底层的一些组件注入到对象中
 * 通过对应的Processor进行处理
 * 比如：
 * ApplicationContextAware  -->  ApplicationContextAwareProcessor
 */
@Data
public class Person implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext context;

    private String name;
    private int age;

    public Person() {
    }

    public void init() {
        System.out.println("person init...");
    }

    public void destroy() {
        System.out.println("person destroy...");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean的名字 " + name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println(resolver.resolveStringValue("${os.name}"));
    }
}
