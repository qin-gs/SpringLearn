package com.spring.learn.format;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MessageFormatTest {

    @Test
    public void test() {
        String pattern1 = "{0}, {1}, {2}";
        String pattern2 = "{1, time, short} {1, date, long}";

        Object[] params = {"John", new GregorianCalendar().getTime(), 1E3};

        String msg1 = MessageFormat.format(pattern1, params);

        MessageFormat mf = new MessageFormat(pattern2, Locale.US);
        String msg2 = mf.format(params);

        System.out.println(msg1);
        System.out.println(msg2);
    }
}
