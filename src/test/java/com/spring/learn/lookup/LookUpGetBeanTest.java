package com.spring.learn.lookup;

import com.spring.learn.bean.GetBeanTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LookUpGetBeanTest {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("./xml/lookupTest.xml");
        GetBeanTest test = (GetBeanTest) bf.getBean("getBeanTest");
        test.showMe();
    }
}
