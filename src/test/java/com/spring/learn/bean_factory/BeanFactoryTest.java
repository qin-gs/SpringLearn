package com.spring.learn.bean_factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;

@DisplayName("BeanFactory ApplicationContext 的使用区别")
public class BeanFactoryTest {

    /**
     * BeanFactory的使用
     */
    @Test
    public void beanFactoryTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册 BeanPostProcessor
        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());

        // 注册 BeanFactoryPostProcessor
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("bean.xml");
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setLocation(new FileSystemResource("jdbc.properties"));
        configurer.postProcessBeanFactory(beanFactory);
    }

    /**
     * ApplicationContext的使用
     */
    @Test
    public void applicationContextTest() {
        // 自动注册 BeanPostProcessor, BeanFactoryPostProcessor
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("");
    }
}
