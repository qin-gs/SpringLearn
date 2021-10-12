package com.spring.learn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectJTest {

    @Pointcut("execution( * com.spring.learn.aop.*.testAop(..))")
    public void test() {

    }

    @Before("test()")
    public void before() {
        System.out.println("before");
    }

    @After("test()")
    public void after() {
        System.out.println("after");
    }

    @Around("test()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("around before");
        Object ans = null;
        try {
            ans = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around after");
        return ans;
    }
}
