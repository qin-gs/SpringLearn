package com.spring.learn.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * AbstractAutowireCapableBeanFactory
 * <p>
 * 先执行 populateBean 给bean属性赋值
 * <p>
 * 1. applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * 遍历容器中所有的BeanPostProcessor，执行postProcessBeforeInitialization，任意一个返回null，直接返回不执行后面的
 * <p>
 * 2. invokeInitMethods(beanName, wrappedBean, mbd); 执行自定义的初始化
 * 3. applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * <p>
 * spring中BeanPostProcessor的多种使用
 * 1. ApplicationContextAwareProcessor 注入ioc容器(context)
 * 2. BeanValidationPostProcessor 进行数据校验
 * 3. InitDestroyAnnotationBeanPostProcessor 处理@PostConstruce 和 @PreDestroy 注解
 * 4. AutowiredAnnotationBeanPostProcessor 处理@Autowired 注解
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 在init初始化方法之前调用
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // System.out.println("postProcessBeforeInitialization...");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // System.out.println("postProcessAfterInitialization...");
        return bean;
    }
}
