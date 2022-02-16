package com.spring.learn.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 使用 {@code @Lookup} 注解在单例对象中注入其他作用域对象
 */
public class LookUpGetBeanAnnotationTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        A a = context.getBean(A.class);

        // 调用被 @Lookup 修饰的方法每次都会返回新对象
        System.out.println(a.getB());
        System.out.println(a.getB());
        System.out.println(a.getB());

        // 类名只有一个字母，context 中存储的是小写
        // A 对象是一个被增强的代理对象，名字为 A$$EnhancerBySpringCGLIB$$bbc87bec@6f70f32f
        System.out.println(context.getBean("a"));
    }
}

@Configuration
@ComponentScan("com.spring.learn.lookup")
class Config {

    @Bean
    public C c() {
        return new C();
    }
}

@Component
class A {

    // 使用 @Autowired 注入的对象虽然作用域是 prototype，但是是同一个
    @Autowired
    B b;

    // 使用 @Lookup 修改的方法返回的对象，每次都会新创建一个
    // 这个方法会被代理掉，不需要有返回值，只需要返回类型，根据返回类型从 context 中返回一个bean
    @Lookup
    public B getB() {
        return null;
    }
}

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
class B {

    @Autowired
    A a;

}

class C {

}
