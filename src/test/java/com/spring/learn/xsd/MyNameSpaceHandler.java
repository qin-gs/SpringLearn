package com.spring.learn.xsd;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 将组件注册到spring容器
 * 加载自定义标签的流程：
 * 遇到自定义标签之后去spring.handlers和spring.schemas中找对应的handler和xsd，默认位置是META-INF，
 * 找到对应的handler和解析元素的Parser，完成自定义元素的解析
 * spring将自定义标签的解析工资委托给用户实现
 */
public class MyNameSpaceHandler extends NamespaceHandlerSupport {

    /**
     * 遇到user开头的标签时交给自定义的Parser
     */
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
