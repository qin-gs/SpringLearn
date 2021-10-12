// package com.spring.learn.junit5;
//
// import org.junit.jupiter.api.RepeatedTest;
// import org.junit.jupiter.api.RepetitionInfo;
// import org.junit.jupiter.api.TestInfo;
// import org.junit.jupiter.api.parallel.Execution;
// import org.junit.jupiter.api.parallel.ExecutionMode;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.ValueSource;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
// public class ParallelTest {
//
// 	private static final Logger log = LoggerFactory.getLogger(ParallelTest.class);
//
// 	/**
// 	 * 一个测试类的某个测试方法，在重复测试或参数化测试的时候多个线程并发执行
// 	 */
// 	// @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
// 	@Execution(ExecutionMode.CONCURRENT)
// 	@RepeatedTest(value = 1000, name = "{displayName}-->{currentRepetition}/{totalRepetitions}")
// 	public void sameThreadTest(TestInfo testInfo, RepetitionInfo info) {
// 		log.info("{}, {}, {}, {}", testInfo.getTestClass(), testInfo.getTestMethod(), testInfo.getDisplayName(), testInfo.getTags());
// 		log.info("{}, {}", info.getCurrentRepetition(), info.getTotalRepetitions());
// 	}
//
// 	@Execution(ExecutionMode.CONCURRENT)
// 	@ParameterizedTest
// 	@ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
// 	public void intTest(int candidate) {
// 		log.info("{}", candidate);
// 	}
// }
