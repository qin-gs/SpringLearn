package com.spring.learn.junit5;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class DefineAnnotationTest {

    @Test
    @Hard
    public void testHard() {
        System.out.println("hard");
    }

    @Test
    public void test() {
        System.out.println("not hard");
    }
}

/**
 * 自定义一个注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("hard")
@interface Hard {
}
