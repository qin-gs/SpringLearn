package com.spring.learn.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 按顺序执行
 * 添加 -> 更新 -> 查询 -> 删除
 */
// @FixMethodOrder
public class OrderTest {

    @Test
    public void testAAdd() {
        System.out.println(" a method");
        Assertions.assertEquals(1, 2);
    }
}
