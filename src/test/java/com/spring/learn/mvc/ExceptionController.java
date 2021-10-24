package com.spring.learn.mvc;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 处理所有被RestController注解的方法
 */
@ControllerAdvice(basePackages = "com.spring.learn.mvc", annotations = RestController.class)
public class ExceptionController {

}
