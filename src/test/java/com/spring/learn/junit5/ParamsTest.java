package com.spring.learn.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParamsTest {
    @ParameterizedTest // 代替@Test
    @DisplayName("参数化测试")
    @NullSource // 传入null
    @EmptySource // 传入空字符串
    // @NullAndEmptySource
    @ValueSource(strings = {"aa", "ab", "bb"})
    public void paramsTest(String candidate) {
        assertThat(candidate).as("字符串参数化测试")
                .startsWith("a")
                .endsWith("b")
                .hasSize(2);
    }

    @ParameterizedTest
    // @EnumSource // 根据参数决定使用哪个枚举，默认全部传进去
    @EnumSource(mode = EnumSource.Mode.INCLUDE, names = {"SMALL", "BIG"}) // 选择哪些执行 或 哪些不执行
    public void enumTest(Type type) {
        System.err.println("type = " + type);
    }

    public static Stream<String> stringProvider() {
        return Stream.of("aa", "ab", "bb");
    }

    /**
     * 方法必须是静态的(或被@TestInstance修改)，方法返回的元素集合作为测试方法的入参
     * 不在一个方法中是需要指定包名类名方法名
     * 如果不指定，会寻找与测试方法同名的静态方法
     */
    @ParameterizedTest
    @MethodSource("stringProvider")
    public void methodTest(String candidate) {
        System.out.println(candidate);
    }

    /**
     * 可以通过 name 字段自定义测试执行名称
     */
    @ParameterizedTest(name = "序号[{index}], 第一个参数[{0}], 第二个参数[{1}]")
    @CsvSource(value = {
            "small, 0",
            "big, 1",
            "unknown, 2",
            "NIL, -1"
    }, nullValues = "NIL") // 将NIL这个字符串识别为null，传进去
    // @CsvFileSource(files = "src/test/resources/csv.csv", numLinesToSkip = 1) // 指定文件，跳过某些行
    public void csvTest(String name, int code) {
        System.out.println(name + ": " + code);
    }
}

enum Type {
    SMALL,
    BIG,
    UNKNOWN
}