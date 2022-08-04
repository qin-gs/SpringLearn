package com.spring.learn.lite;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * lite 模式的配置，不能声明 @Bean 之间的依赖，也就是说不能通过方法调用来依赖其它 Bean；
 * <pre>
 * 1. 配置类本身不会被 CGLIB 增强，放进 IoC 容器内的就是本尊
 * 2. 对于内部类是没有限制的：可以是 Full 模式 或者 Lite 模式
 * 3. 配置类内部不能通过方法调用来处理依赖，否则每次生成的都是一个新实例而并非 IoC 容器内的单例
 * 4. 配置类就是一个普通类，所以 @Bean 方法可以使用 private/final/static 等进行修饰
 * </pre>
 *
 * <pre>
 * 1. 类上标注有 @Component 注解
 * 2. 类上标注有 @ComponentScan 注解
 * 3. 类上标注有 @Import 注解
 * 4. 类上标注有 @ImportResource 注解
 * 5. 若类上没有任何注解，但类内存在 @Bean 方法
 * 6. 标注有 @Configuration(proxyBeanMethods = false)，此值默认是 true，需要显示改为 false 才算是 lite 模式
 * </pre>
 */
@Configuration
@ComponentScan("com.spring.learn.lite")
public class LiteConfig {

}
