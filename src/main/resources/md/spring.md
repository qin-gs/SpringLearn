@Configuration

AnnotationConfigApplicationContext -> refresh() -> invokeBeanFactoryPostProcessors()  
对加了该注解的类放入map, 全部进行cglib代理(继承该类；jdk动态代理基于接口)
先判断是否已经被代理(通过接口判断EnhancedConfiguration.class extends BeanFactoryAware)  
同时为代理类加一个字段 BeanFactory $$beanFactory(BeanFactoryAwareGeneratorStrategy.class)
重写被@Bean注解的方法，通过上面的$$beanFactory生成  
(用过滤器实现MethodInterceptor如果是第一次生成(判断方法名是否相同)去new, 否则用工厂获取)

Spring容器  
BeanDefinition, BeanDefinitionMap, BeanFactoryPostProcessor, BeanFactory, singletonObjects...

spring bean的生命周期  
AbstractBeanFactory.doGetBean()  
getSingleton(beanName)  
从singletonObjects中获取bean，不为空直接返回  
DefaultSingletonBeanRegistry  
isSingletonCurrentlyInCreation 判断对象是否在创建中(bean有循环依赖，所以有中间状态, singletonsCurrentlyInCreation记录正在创建的bean)  
bean 先构造函数 再

9个地方调用了6个后置处理器

1. ApplicationContextAwareProcessor
2. ImportAwareBeanPostProcessor
3. BeanPostProcessorChecker
4. CommonAnnotationBeanPostProcessor (@Resource)
5. AutowiredAnnotationBeanPostProcessor (@Autowired)
6. ApplicationListenerDetector

populateBean 完成@Autowired
org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.resolveBeforeInstantiation
InstantiationAwareBeanPostProcessor

BeanPostProcessor 插手bean的初始化过程  
实例化 整个过程 初始化 new之后

new创建对象 -> 执行所有的BeanPostProcessor -> @PostConstruct







