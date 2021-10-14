package com.spring.learn.message;

import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.GregorianCalendar;
import java.util.Locale;

public class MessageSourceTest {
    @Test
    public void test() {
        String[] configs = {"xml/messages.xml"};
        MessageSource source = new ClassPathXmlApplicationContext(configs);
        Object[] params = {"John", new GregorianCalendar().getTime()};

        String msg1 = source.getMessage("test", null, Locale.US);
        String msg2 = source.getMessage("test", null, Locale.CHINA);

        System.out.println(msg1);
        System.out.println(msg2);

    }
}
