package com.spring.learn.editor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@DisplayName("PropertyEditor 测试")
public class EditorTest {

    /**
     * PropertyEditor
     * 读取xml文件时设置bean的属性
     * String -> Class, Boolean, File, Collection, Date...
     */
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("xml/editor.xml");
        DependsOnExoticType bean = context.getBean(DependsOnExoticType.class);
        System.out.println(bean);
    }
}
