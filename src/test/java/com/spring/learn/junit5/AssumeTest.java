package com.spring.learn.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * 假设
 * | 方法          | 描述 |
 * | ------------ | -------------------|
 * | assumeFalse  | 假设为false时才会执行，如果为true，那么将会直接停止执行|
 * | assumeTrue   | 假设为true时才会执行，如果为false，那么将会直接停止执行|
 * | assumingThat | assumingThat接受一个函数式接口Executable，假设为true时执行，将会执行Executable，否则不会执行Executable。|
 * <p>
 * Assertions: 断言类 assertTrue(false) 会抛出异常 AssertionFailedError 测试失败
 * Assumptions: 假设类 assumeTrue(false) TestAbortedException 跳过
 */
// @ActiveProfiles("test") // 这个注解会取读取application-test.xml配置文件
public class AssumeTest {

    @Test
    public void assumeTrueTest() {
        // assumeTrue(false);
        assumeTrue(false, "默认信息"); // assume失败，会将第二个参数作为失败信息输出
        System.out.println("到不了这里");
    }

    @Test
    public void assumeThatTest() {
        // assumeTrue(false, () ->"默认信息");
        // assumeThat 如果是false，只影响后面的executable，不影响下面的代码执行
        // 不抛出异常
        assumingThat(false, () -> System.out.println("到不了这里"));
        System.out.println("上面是false也可以到这里");
    }
}
