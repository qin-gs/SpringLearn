package com.spring.learn.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * |注解                 | 描述|
 * |--------------------|----------------------|
 * | @Test              | 表示方法是一种测试方法。 与JUnit 4的@Test注解不同，此注释不会声明任何属性。 |
 * | @ParameterizedTest | 表示方法是参数化测试 |
 * | @RepeatedTest      | 表示方法是重复测试模板 |
 * | @TestFactory       | 表示方法是动态测试的测试工程 |
 * | @DisplayName       | 为测试类或者测试方法自定义一个名称 |
 * | @BeforeEach        | 表示方法在每个测试方法运行前都会运行 |
 * | @AfterEach         | 表示方法在每个测试方法运行之后都会运行 |
 * | @BeforeAll         | 表示方法在所有测试方法之前运行 |
 * | @AfterAll          | 表示方法在所有测试方法之后运行 |
 * | @Nested            | 表示带注解的类是嵌套的非静态测试类,@BeforeAll和@AfterAll方法不能直接在@Nested测试类中使用，除非修改测试实例生命周期。 |
 * | @Tag               | 用于在类或方法级别声明用于过滤测试的标记 |
 * | @Disabled          | 用于禁用测试类或测试方法 |
 * | @ExtendWith        | 用于注册自定义扩展，该注解可以继承 |
 * <p>
 * |断言               |描述|
 * |------------------|------------------------|
 * |assertEquals      | 断言预期值和实际值相等，接收supplier作为第三个参数，判断不通过是会通过get方法获取字符串作为失败消息，成功的话不会执行|
 * |assertAll         | 分组断言,执行其中包含的所有断言，只要有一个报错会导致整个不通过|
 * |assertArrayEquals | 断言预期数组和实际数组相等|
 * |assertFalse       | 断言条件为假|
 * |assertNotNull     | 断言不为空|
 * |assertSame        | 断言两个对象相等|
 * |assertTimeout     | 断言超时|
 * |assertTimeoutPreemptively     | 断言超时，开一个新线程执行execute方法，一旦超时就会停止，不会等execute执行完成|
 * |assertThrows      | 测试执行的方法是否抛出指定类型的异常，不抛出异常或类型不对都会失败|
 * |fail              | 使单元测试失败|
 */
@DisplayName("junit5 test")
public class Junit5Test {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("before all tests");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each tests");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("after each tests");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("after all tests");
    }

    @Test
    @Disabled
    @DisplayName("a disabled test")
    public void disabledTest() {

    }

    @Test
    @DisplayName("a real test")
    public void realTest() {
        assertEquals("qqq", "qqq");
        assertTrue("qqq" == new String("qqq"), "error message");
        assertAll("a string", () -> assertEquals("string", "error string"), () -> assertEquals("person", "a person"));
    }

    @Test
    @DisplayName("分组断言")
    public void dependentAssertionsTest() {
        assertAll("properties",
                () -> {
                    // 代码块中，如果断言失败，后面的不会继续运行
                    String firstName = "qqq";
                    assertNotNull(firstName);
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("q")),
                            () -> assertTrue(firstName.endsWith("a")));
                },
                // 分组断言，上一个失败，下一个也会运行
                () -> {
                    String lastName = "gs";
                    assertNotNull(lastName);
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("g")),
                            () -> assertTrue(lastName.endsWith("s"))
                    );
                });
    }

    @Test
    @DisplayName("exception test")
    public void exceptionTest() {
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> {
                    throw new IllegalArgumentException("a message");
                });
        assertEquals("a message", throwable.getMessage());
    }

    @Test
    @DisplayName("timeout")
    public void timeoutTest() {
        // 5秒钟完成，测试通过
        assertTimeout(Duration.ofSeconds(5), () -> System.out.println("time out test"));
        assertTimeout(Duration.ofSeconds(1), () -> Thread.sleep(2000));
    }

    @Test
    @DisplayName("timeout result")
    public void timeoutResult() {
        // 断言成功可以返回结果
        String result = assertTimeout(Duration.ofMinutes(1), () -> "result");
        assertEquals("result", result);
    }

    @Test
    @DisplayName("timeout failed")
    public void timeoutFailed() {
        // 如果指定时间内没有执行完毕，断言失败会立刻返回，不会等执行完成
        assertTimeoutPreemptively(Duration.ofMillis(10), () -> Thread.sleep(15));
    }
}
