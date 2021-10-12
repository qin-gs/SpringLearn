package com.spring.learn.condition;

import com.spring.learn.bean.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@Conditional(MagicCondition.class)
public class ConditionalTest {

    @Bean
    // @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
    public Book getBook() {
        return new Book();
    }
}


class MagicCondition implements Condition {

    /**
     * 设置条件
     *
     * @param context  the condition context
     * @param metadata 检查带有@Bean注解的方法上还有什么其他注解
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env.containsProperty("magic");
    }
}
