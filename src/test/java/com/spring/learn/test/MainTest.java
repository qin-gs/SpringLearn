package com.spring.learn.test;

import com.spring.learn.bean.Person;
import com.spring.learn.configurable.Config;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.Map;

public class MainTest {

    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    /**
     * 打印容器中当前所有的对象
     */
    @Test
    public void allBeanTest() {
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
    }

    /**
     * 配置运行环境
     */
    @Test
    public void allBeanProfileTest() {
        // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // context.getEnvironment().setActiveProfiles("dev");
        // context.register(Config.class);
        // context.refresh();
        // String[] names = context.getBeanDefinitionNames();
        // Arrays.stream(names).forEach(System.out::println);
    }

    @Test
    public void test() {
        String[] names = context.getBeanNamesForType(Person.class);
        Arrays.stream(names).forEach(System.out::println);

        Map<String, Person> beans = context.getBeansOfType(Person.class);
        System.out.println(beans);
    }

    @Test
    public void conditionTest() {
        ConfigurableEnvironment environment = context.getEnvironment();
        System.out.println(environment.getProperty("os.name"));
        System.out.println(environment);
    }

    @Test
    public void factoryBeanTest() {
        // 通过&符号，获取 factoryBean(BeanFactory中规定)
        Object factoryBean = context.getBean("&getMyFactoryBean");
        System.out.println(factoryBean);

        // 直接获取factoryBean，返回的是getObject返回的对象
        Object bean = context.getBean("getMyFactoryBean");
        System.out.println(bean);
    }

}
