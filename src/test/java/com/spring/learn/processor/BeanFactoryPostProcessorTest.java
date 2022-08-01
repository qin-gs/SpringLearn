package com.spring.learn.processor;

import com.spring.learn.bean.SimplePostProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * DefaultListableBeanFactory 的父类 AbstractBeanFactory#doGetBean 方法中进行 bean 的创建
 * <pre>
 *      1. 检查手动注册的单例集合缓存中是否含有该bean对象，若有，则取出返回，否则继续执行；
 *      2. 检查该bean是否已经创建，从而判断是否属于循环引用，若是，抛出异常返回，否则继续执行；
 *      3. 判断bean工厂中是否存在该bean definition，若存在，则取出返回，否则继续执行；
 *      4. 初始化该bean所依赖的bean对象；
 *      5. 判断该bean是否是单例模式（singleton），若是，创建单例对象，否则继续执行；
 *      6. 判断该bean是否是原型模式（prototype），若是，创建原型对象，否则继续执行；
 *      7. 创建自定义类型（scope）bean对象。
 * </pre>
 */
public class BeanFactoryPostProcessorTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("./xml/bean-factory-post-processor.xml");
        // BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) context.getBean("bfpp");
        ConfigurableListableBeanFactory bf = new XmlBeanFactory(new ClassPathResource("./xml/bean-factory-post-processor.xml"));
        BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) bf.getBean("bfpp");
        bfpp.postProcessBeanFactory(bf);
        SimplePostProcessor bean = bf.getBean(SimplePostProcessor.class);
        System.out.println(bfpp);
        System.out.println(bean);
    }
}
