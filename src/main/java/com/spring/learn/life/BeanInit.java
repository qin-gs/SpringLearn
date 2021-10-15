package com.spring.learn.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean生命周期的顺序如下
 * <pre>
 *      1. constructor
 *      2. BeanNameAware
 *      3. BeanFactoryAware
 *      4. ApplicationContextAware
 *      5. BeanPostProcessor postProcessBeforeInitialization
 *      6. InstantiationAwareBeanPostProcessor postProcessBeforeInitialization
 *      7. @PostConstruct
 *      8. InitializingBean afterPropertiesSet
 *      9. init-method
 *     10. BeanPostProcessor postProcessAfterInitialization
 *     11. InstantiationAwareBeanPostProcessor postProcessAfterInitialization
 *     12. DestructionAwareBeanPostProcessor postProcessBeforeDestruction
 *     13. @PreDestroy
 *     14. DisposableBean
 *     15. destroy-method
 * </pre>
 */
public class BeanInit implements InitializingBean, DisposableBean, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware {

    public BeanInit() {
        System.out.println("1. constructor");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("2. BeanNameAware");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3. BeanFactoryAware");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("4. ApplicationContextAware");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("7. @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("8. InitializingBean afterPropertiesSet");
    }

    /**
     * <bean init-method="init"></bean>
     */
    public void init() {
        System.out.println("9. init-method");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("13. @PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("14. DisposableBean");
    }

    /**
     * <bean destroy-method="destroy_"></bean>
     */
    public void destroy_() {
        System.out.println("15. destroy-method");
    }

}
