package com.spring.learn.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean生命周期的顺序如下
 * <pre>
 *      1. BeanDefinitionRegistryPostProcessor postProcessBeanDefinitionRegistry
 *      2. BeanFactoryPostProcessor postProcessBeanFactory
 *      3. InstantiationAwareBeanPostProcessor postProcessBeforeInstantiation
 *      4. constructor
 *      5. InstantiationAwareBeanPostProcessor postProcessAfterInstantiation
 *      6. BeanNameAware
 *      7. BeanFactoryAware
 *      8. ApplicationContextAware
 *      9. BeanPostProcessor postProcessBeforeInitialization
 *     10. @PostConstruct
 *     11. InitializingBean afterPropertiesSet
 *     12. init-method
 *     13. BeanPostProcessor postProcessAfterInitialization
 *     14. DestructionAwareBeanPostProcessor postProcessBeforeDestruction
 *     15. @PreDestroy
 *     16. DisposableBean
 *     17. destroy-method
 * </pre>
 * 如果 3 返回了一个对象(aop)，会直接跳到 13
 */
public class BeanInit implements InitializingBean, DisposableBean, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware {

    public BeanInit() {
        System.out.println("4. constructor");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("6. BeanNameAware");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("7. BeanFactoryAware");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("8. ApplicationContextAware");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("10. @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("11. InitializingBean afterPropertiesSet");
    }

    /**
     * <bean init-method="init"></bean>
     */
    public void init() {
        System.out.println("12. init-method");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("15. @PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("16. DisposableBean");
    }

    /**
     * <bean destroy-method="destroy_"></bean>
     */
    public void destroy_() {
        System.out.println("17. destroy-method");
    }

}
