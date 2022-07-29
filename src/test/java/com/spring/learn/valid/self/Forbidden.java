package com.spring.learn.valid.self;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ForbiddenValidator.class)
@Documented
public @interface Forbidden {

    /**
     * 默认的错误消息
     */
    String message() default "{forbidden.word}";

    /**
     * 分组
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payloads() default {};

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Forbidden[] value();
    }

}
