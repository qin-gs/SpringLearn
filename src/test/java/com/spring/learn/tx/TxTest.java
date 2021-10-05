package com.spring.learn.tx;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@DisplayName("事务测试")
public class TxTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/config/tx.xml");
        TxService bean = context.getBean(TxService.class);
        bean.getTest();
    }
}
