package com.spring.learn.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 嵌套测试
 * 在测试类中传简一些内部类
 * {@code @Nested}注解以内部类为单位执行测试展现结果
 */
@DisplayName("嵌套测试")
public class NestedTest {

    @Nested
    @DisplayName("查询类")
    class Find {
        @Test
        public void findById() {
            System.out.println("findById");
        }
    }

    @Nested
    @DisplayName("删除类")
    class Delete {
        @Test
        public void deleteById() {
            System.out.println("deleteById");
        }
    }
}
