package com.spring.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP 在程序运行期动态的将某段代码切入到指定的位置运行
 * 前置通知
 * 后置通知
 * 正常返回通知
 * 异常返回通知
 * 环绕通知
 * <p>
 * 1. 定义业务逻辑类
 * 2. 定义切面类
 * 3. 给切面类的目标方法标注那个方法运行
 * 4. 将切面类 和 业务逻辑类 放入容器
 * 5. 告诉spring哪个类是切面类(@Aspect)
 * 6. 开启基于注解的aop模式(@EnableAspectJAutoProxy)
 * <p>
 * 原理 @EnableAspectJAutoProxy
 * 导入了 @Import(AspectJAutoProxyRegistrar.class(ImportBeanDefinitionRegistrar.class))
 * 1. 利用AspectJAutoProxyRegistrar向容器中注入一个bean(名称是internalAutoProxyCreator 类型是AnnotationAwareAspectJAutoProxyCreator.class)
 * 2. 注入的bean继承了PostProcessor接口 和 Aware接口 (SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware)
 *   setBeanFactory
 *
 *
 * 流程
 * 1. 传入配置类，创建ioc容器
 * 2. 注册配置类，调用refresh方法
 * 3. registerBeanPostProcessors; 注册bean的后置处理器来方便拦截bean的创建// Register bean processors that intercept bean creation.
 *   1. 先获取ioc容器中已经定义了的需要创建对象的所有BeanPostProcessor
 *   2. 给容器中加别的BeanPostProcessor
 *   3. 优先注册实现PriorityOrdered接口的BeanPostProcessor
 *   4. 然后注册实现Ordered接口的BeanPostProcessor
 *   5. 注册没实现上面两个接口的BeanPostProcessor
 *   6. 注册BeanPostProcessor(创建BeanPostProcessor对象，放入容器)
 *     先创建internalAutoProxyCreator(AnnotationAwareAspectJAutoProxyCreator.class)
 *       1. 创建bean实例
 *       2. populateBean 给bean的属性赋值
 *       3. initializeBean 初始化bean
 *         1. invokeAwareMethods 处理Aware接口的方法回调
 *         2. applyBeanPostProcessorsBeforeInitialization 应用后置处理器的postProcessBeforeInitialization
 *         3. invokeInitMethods 执行自定义的初始化方法
 *         4. applyBeanPostProcessorsAfterInitialization 应用后置处理器的postProcessAfterInitialization
 *       4. BeanPostProcessor(internalAutoProxyCreator) 创建成功
 *   7. 把BeanPostProcessor注册到BeanFactory中
 *     beanFactory.addBeanPostProcessor(postProcessor)
 *
 * --- 以上完成 AnnotationAwareAspectJAutoProxyCreator 的创建和注册 ---
 *
 *  4. 完成BeanFactory的初始化(完成剩下bean的注册)
 *    1. 遍历容器中所有的bean，创建对象getBean(beanName)
 *      doGetBean -> getSingleton
 *    2. 创建bean
 *      AnnotationAwareAspectJAutoProxyCreator会在所有的bean创建之前做一个拦截，调用postProcessBeforeInstantiation
 *      1. 先从容器中获取当前bean，如果能获取到直接使用，否则去创建(创建好的都会被缓存起来)
 *      2. createBean 创建bean AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前尝试返回bean实例
 *        [BeanPostProcessor 在bean对象创建完成初始化前后调用的]
 *        [InstantiationAwareBeanPostProcessor 在创建bean实例之前尝试用后置处理器返回对象]
 *        1. resolveBeforeInstantiation 解析BeforeInstantiation // 希望后置处理器创建并返回代理对象，如果还没返回自己去创建(走上面的3.6流程)
 *          后置处理器尝试返回对象，先遍历所有的后置处理器，如果是InstantiationAwareBeanPostProcessor，返回调用它的postProcessBeforeInstantiation
 *        2. doCreateBean 真正的去创建bean
 */
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

}
