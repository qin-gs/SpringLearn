package com.spring.learn.converter;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Date;

public class ConverterTest {
    @Test
    public void test() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new String2DateConverter());

        String date = "2021-07-26 10:58:23";
        Date d = service.convert(date, Date.class);
        System.out.println(d);
    }
}
