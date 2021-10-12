package com.spring.learn.aware;

import com.spring.learn.util.AwareTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AwareInterfaceTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("xml/aware.xml");
        AwareTest bean = (AwareTest) context.getBean("beanFactoryAware");
        bean.testAware();
        System.out.println(bean);
    }
}
