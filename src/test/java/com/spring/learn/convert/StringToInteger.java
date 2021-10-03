package com.spring.learn.convert;

import org.springframework.core.convert.converter.Converter;

public class StringToInteger implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
