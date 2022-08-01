package com.spring.learn.ignore;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IgnoreTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/ignore.xml");
        // ListHolder 中 List 类型的字段在 xml 中有配置但没有注入
        ListHolder bean = context.getBean(ListHolder.class);
        System.out.println(bean.getList());
    }
}
