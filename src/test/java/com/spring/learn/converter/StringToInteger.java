package com.spring.learn.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * https://blog.csdn.net/weixin_38560512/article/details/115583234
 */
public class StringToInteger implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
