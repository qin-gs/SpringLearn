package com.spring.learn.servlet.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    public String exceptionHandler() {
        return "error";
    }
}
