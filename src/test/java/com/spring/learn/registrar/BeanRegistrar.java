package com.spring.learn.registrar;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 手动注册 bean，@EnableAspectJAutoProxy
 * 使用场景：
 * <pre>
 *     1. 外部依赖类无法自动扫描和初始化，使用ImportBeanDefinitionRegistrar手动注入和初始化
 *     2. 外部依赖服务只有接口没有实现，重写 ImportBeanDefinitionRegistrar 接口拿到后根据协议和接口生成代理实现并实例化,供本地化调用
 * </pre>
 * {@code @Import} 注解被 ConfigurationClassParser#doProcessConfigurationClass 处理
 */
public class BeanRegistrar {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CustomBean bean = context.getBean(CustomBean.class);
        bean.hello();
    }
}

@Configuration
@Import(CustomBeanRegistrar.class)
class Config {

}

/**
 * 手动注册 bean
 */
class CustomBeanRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 创建一个 BeanDefinition
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CustomBean.class);
        builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        // 可以指定构造函数的参数值
        // beanDefinition.getConstructorArgumentValues().addGenericArgumentValue();

        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, CustomBean.class.getSimpleName());
        // 注册进去
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }
}

class CustomBean {

    public void hello() {
        System.out.println("hello custom bean");
    }
}
