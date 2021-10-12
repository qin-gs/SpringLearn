package com.spring.learn.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

/**
 * 自定义条件
 */
public class DefineTest {
    boolean condition() {
        return true;
    }

    @Test
    @EnabledIf("condition")
    public void conditionTrue() {
        System.out.println("condition true");
    }

    @Test
    @DisabledIf("condition")
    public void conditionFalse() {
        System.out.println("condition false");
    }
}
