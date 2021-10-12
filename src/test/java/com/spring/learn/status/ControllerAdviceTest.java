package com.spring.learn.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 修改统一异常处理的状态码
 */
@ControllerAdvice
@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class ControllerAdviceTest {

    @ExceptionHandler(value = {ArithmeticException.class, ClassCastException.class})
    public ModelAndView fix(Exception e) {
        return new ModelAndView();
    }
}
