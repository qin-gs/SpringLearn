package com.spring.learn.xsd;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 将组件注册到 spring 容器
 * 加载自定义标签的流程：
 * 遇到自定义标签之后去 spring.handlers 和 spring.schemas 中找对应的 handler 和 xsd，默认位置是 META-INF，
 * 找到对应的 handler 和解析元素的 Parser，完成自定义元素的解析
 * spring 将自定义标签的解析工作委托给用户实现
 */
public class MyNameSpaceHandler extends NamespaceHandlerSupport {

    /**
     * 遇到 user 开头的标签时交给自定义的 Parser
     */
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
