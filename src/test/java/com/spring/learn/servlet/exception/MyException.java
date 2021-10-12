package com.spring.learn.servlet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 将异常映射到指定的状态码
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not found")
public class MyException extends RuntimeException {
    private static final long serialVersionUID = 159558069149777113L;
}
