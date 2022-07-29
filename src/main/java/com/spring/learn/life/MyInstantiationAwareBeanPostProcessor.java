package com.spring.learn.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Objects;

/**
 * 对象创建前后，对象初始化前后 进行扩展；
 * 触发时机：AbstractAutowireCapableBeanFactory#createBean -> resolveBeforeInstantiation
 * <p>
 * 1、postProcessBeforeInstantiation 调用时机为 bean 实例化 (Instantiation) 之前 如果返回了 bean 实例,
 * 则会替代原来正常通过 target bean 生成的 bean 的流程. 典型的例如 <strong>aop</strong> 返回 proxy 对象. 此时 bean 的执行流程将会缩短,
 * 只会执行 BeanPostProcessor#postProcessAfterInitialization 接口完成初始化。否则调用 doCreateBean
 * <p>
 * 2、postProcessAfterInstantiation 调用时机为 bean 实例化 (Instantiation) 之后和任何初始化 (Initialization) 之前。
 * <p>
 * 3、postProcessProperties 调用时机为 postProcessAfterInstantiation 执行之后并返回 true,
 * 返回的 PropertyValues 将作用于给定 bean 属性赋值. spring 5.1 之后出现以替换 @Deprecated 标注的 postProcessPropertyValues
 * <p>
 * 4、postProcessPropertyValues 已经被标注 @Deprecated，后续将会被 postProcessProperties 取代。
 */
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    private final String beanInit = "beanInit";

    // InstantiationAwareBeanPostProcessor 扩展的方法 (创建对象前调用)

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (Objects.equals(beanName, beanInit)) {
            System.out.println("3. InstantiationAwareBeanPostProcessor postProcessBeforeInstantiation");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, beanInit)) {
            System.out.println("5. InstantiationAwareBeanPostProcessor postProcessAfterInstantiation");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    // postProcessAfterInstantiation 决定要不要调用下面两个方法
    // 下面两个方法可以修改 PropertyValues (当前对象只调用了构造函数，各个字段都还是 null，pvs 是用来初始化字段的)

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 该方法优先于 postProcessPropertyValues 执行 (AbstractAutowireCapableBeanFactory#populateBean)
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return InstantiationAwareBeanPostProcessor.super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }


    // BeanPostProcessor 的方法 (创建对象后，调用初始化(@PostConstruct, afterPropertiesSet, init-method)方法前调用)

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, beanInit)) {
            System.out.println("9. BeanPostProcessor postProcessBeforeInitialization");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, beanInit)) {
            System.out.println("13. BeanPostProcessor postProcessAfterInitialization");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

}
