package com.spring.learn.junit5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

public class MySourceTest {

    /**
     * 使用自定义的数据源
     */
    @ArgumentsSource(MyArgumentsProvider.class)
    public void argumentsSourceTest(String candidate) {
        System.out.println(candidate);
    }

    /**
     * 参数类型自动转换
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void argumentConversionTest(double candidate) {
        System.out.println(candidate);
    }

    @ParameterizedTest
    @ValueSource(strings = {"03.08.2021", "31.08.2021"})
    public void argumentConversionTest(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate candidate) {
        System.out.println(candidate);
    }

    /**
     * 字段聚合，使用自定义的转换器
     * 或者直接使用参数 ArgumentAccessor
     */
    @ParameterizedTest
    @CsvSource({
            "a, 12, SMALL",
            "c, 34, BIG"
    })
    public void argumentAccessorTest(@AggregateWith(UserAggregator.class) User user) {
        System.out.println(user);
    }
}

