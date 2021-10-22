package com.spring.learn.xsd;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * 解析xsd中的定义
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    /**
     * 自定义标签中的class
     */
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        // 从element中解析提取对应的元素
        String userName = element.getAttribute("userName");
        String password = element.getAttribute("password");
        // 将提取到的元素放到builder中，等完成所有bean的解析后统一注册到beanFactory
        if (StringUtils.hasText(userName)) {
            builder.addPropertyValue("userName", userName);
        }
        if (StringUtils.hasText(password)) {
            builder.addPropertyValue("password", password);
        }
    }
}
