package com.spring.learn.junit5;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;

/**
 * 将下划线转成空格
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AdvancedTest {

    @Test
    public void if_it_is_zero() {
        System.out.println("替换名称下划线");
    }
}

/**
 * 使用 测试类名+连接符+测试方法名
 */
@IndicativeSentencesGeneration(separator = ", ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
class IndicativeSentences_Test {
    @Test
    public void if_it_is_zero() {
        System.out.println("替换名称下划线");
    }
}
