package com.spring.learn.config;

import com.spring.learn.bean.Blog;
import com.spring.learn.bean.Person;
import com.spring.learn.condition.LinuxCondition;
import com.spring.learn.condition.MacCondition;
import com.spring.learn.condition.WindowsCondition;
import com.spring.learn.dao.BookDao;
import com.spring.learn.factory.MyFactoryBean;
import com.spring.learn.registry.MyImportBeanDefinitionRegistrar;
import com.spring.learn.selector.MyImportSelector;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configurable
@ComponentScan(
        value = "com.spring.learn",
        includeFilters = {
                // @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                // @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)
        },
        useDefaultFilters = true
)
@Conditional({MacCondition.class})
@Import({Blog.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class Config {

    /**
     * 默认注入的使用方法名作为bean的名字
     * <p>
     * ConfigurableBeanFactory.SCOPE_PROTOTYPE 获取对象的时候才会创建，每次获取返回都不一样
     * ConfigurableBeanFactory.SCOPE_SINGLETON 创建容器的时候就会放进去，以后每次获取都返回同一个
     * WebApplicationContext#SCOPE_REQUEST
     * WebApplicationContext#SCOPE_SESSION
     * <p>
     * Lazy 只针对单例对象， 第一次获取的时候才会创建对象
     */
    @Bean(value = "person", initMethod = "init", destroyMethod = "destroy")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy(value = false)
    public Person getPerson() {
        return new Person("qqq", 123);
    }

    /**
     * 按照条件判断, 满足条件后注册bean对象
     */
    @Conditional({WindowsCondition.class})
    @Bean("person1")
    public Person getPerson01() {
        return new Person("www", '2');
    }

    @Conditional({LinuxCondition.class})
    @Bean("person2")
    public Person getPerson02() {
        return new Person("eee", '3');
    }

    @Bean
    public MyFactoryBean getMyFactoryBean() {
        return new MyFactoryBean();
    }

    /**
     * '@Primary注解'
     * 首选的bean
     */
    @Primary
    @Bean
    public BookDao bookDao2() {
        return new BookDao();
    }
}
