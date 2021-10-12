package com.spring.learn.processor;

import com.spring.learn.bean.SimplePostProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryPostProcessorTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("./xml/bean-factory-post-processor.xml");
        // BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) context.getBean("bfpp");
        ConfigurableListableBeanFactory bf = new XmlBeanFactory(new ClassPathResource("./xml/bean-factory-post-processor.xml"));
        BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) bf.getBean("bfpp");
        bfpp.postProcessBeanFactory(bf);
        SimplePostProcessor bean = bf.getBean(SimplePostProcessor.class);
        System.out.println(bfpp);
        System.out.println(bean);
    }
}
