package com.spring.learn.converter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ConverterController {

    /**
     * 参数使用 String2DateConverter 进行转换
     */
    @GetMapping("converter")
    public void converter(Date date, @RequestHeader(value = "userName") String userName) {

    }
}
