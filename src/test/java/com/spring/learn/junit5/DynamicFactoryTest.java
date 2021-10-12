package com.spring.learn.junit5;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;

/**
 * 动态测试，测试方法在运行期被生成出来，然后运行
 */
public class DynamicFactoryTest {
    @TestFactory
    Iterable<DynamicTest> testFactoryTest() {
        // 每个DynamicTest代表一个测试用例
        DynamicTest first = DynamicTest.dynamicTest("第一个测试用例", () -> System.out.println("第一个测试用例的逻辑代码"));
        DynamicTest second = DynamicTest.dynamicTest("第二个测试用例", () -> System.out.println("第二个测试用例的逻辑代码"));
        return Arrays.asList(first, second);
    }
}
