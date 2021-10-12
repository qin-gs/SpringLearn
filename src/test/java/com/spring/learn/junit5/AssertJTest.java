package com.spring.learn.junit5;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 流式断言
 */
public class AssertJTest {

    @Test
    public void assertJTest() {
        String s = "abcde";
        assertThat(s).as("字符串流式断言")
                .startsWith("a")
                .endsWith("de")
                .hasSize(5);

        int i = 5;
        assertThat(i).as("数字流式断言")
                .isGreaterThan(2)
                .isLessThanOrEqualTo(5)
                .isEqualTo(3);

        Map<String, Object> map = Map.of("a", 1, "b", 2);
        assertThat(map).containsKey("v").hasSize(2);
    }
}
