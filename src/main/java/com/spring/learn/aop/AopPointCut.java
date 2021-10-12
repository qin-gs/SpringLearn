package com.spring.learn.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class AopPointCut {

    @Pointcut("execution(public int com.spring.learn.aop.AopMethod.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint point) {
        System.out.println("方法名 " + point.getSignature().getName());
        System.out.println("参数列表 " + Arrays.toString(point.getArgs()));
        System.out.println("aspect before...");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("aop after...");
    }

    /**
     * joinPoint参数要出现在参数表的第一位
     */
    @AfterReturning(value = "pointcut()", returning = "result")
    public void return_(JoinPoint point, Object result) {
        System.out.println("运行结果 " + result);
    }

    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public void exception_(JoinPoint point, Exception exception) {
        System.out.println(exception.getMessage());
    }

    // @Around()
    // public Object around(JoinPoint point, Object result) {
    //
    // }
}
