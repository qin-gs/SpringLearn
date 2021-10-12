package com.spring.learn.junit5;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class RepeatTest {

    // @RepeatedTest(value = 10, name = "{displayName}-->{currentRepetition}/{totalRepetitions}")
    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    // @RepeatedTest(value = 5)
    public void repeatTest(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName()); // 默认 repetition 1 of 5
        System.out.println(repetitionInfo.getCurrentRepetition());
    }
}
