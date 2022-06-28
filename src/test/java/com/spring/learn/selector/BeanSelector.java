package com.spring.learn.selector;

import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.HashSet;
import java.util.Set;

/**
 * 有些功能我们并不需要 Spring 在一开始就加载进去，而是需要 Spring 帮助我们把这些功能动态加载进去，
 * 这时候这个 ImportSelector 的作用就来了。
 * 我们完全可以把实现这个接口的类做成一个开关，用来开启或者关闭某一个或者某些功能类。
 * 比如 ：@EnableTransactionManagement
 * <p>
 * 源码：ConfigurationClassParser#processImports，
 * 对 ImportSelector 接口的返回值会递归进行解析，把解析到的类全名按照 @Configuration 进行处理
 */
public class BeanSelector {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CustomBean bean = context.getBean(CustomBean.class);
        bean.hello();
    }
}

@Configuration
@Import(CustomBeanSelector.class)
class Config {

}

class CustomBeanSelector implements ImportSelector {

    /**
     * 这个方法返回的类名都会被加载成 bean
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 可以获取注解所在类的元信息
        String packageName;
        try {
            // 获取配置类的名称
            packageName = Class.forName(importingClassMetadata.getClassName()).getPackage().getName();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 可以自己手动扫描一些包
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        // 扫描 CustomBean 及其子类 (也可以排除某些类)
        AssignableTypeFilter typeFilter = new AssignableTypeFilter(CustomBean.class);
        provider.addIncludeFilter(typeFilter);

        Set<String> classes = new HashSet<>();
        // 获取扫描到的类的名称
        provider.findCandidateComponents(packageName).forEach(beanDefinition -> classes.add(beanDefinition.getBeanClassName()));
        return classes.toArray(new String[0]);
    }
}

class CustomBean {
    public void hello() {
        System.out.println("hello custom bean");
    }
}
