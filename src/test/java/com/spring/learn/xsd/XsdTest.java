package com.spring.learn.xsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@DisplayName("自定义标签测试")
public class XsdTest {

    /**
     * 因为代码是放在test目录下的，需要手动将META-INF复制到编译好的test-classes目录下，否则会报错(通配符的匹配很全面, 但无法找到元素xxx的声明)。
     * <p>
     * 加载自定义标签的流程：
     * 遇到自定义标签之后去spring.handlers和spring.schemas中找对应的handler和xsd，默认位置是META-INF，
     * 找到对应的handler和解析元素的Parser，完成自定义元素的解析
     * spring将自定义标签的解析工资委托给用户实现
     */
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/xml/my.xml");
        User bean = context.getBean(User.class);
        System.out.println(bean);
    }
}
