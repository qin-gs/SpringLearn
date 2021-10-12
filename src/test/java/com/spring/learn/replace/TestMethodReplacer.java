package com.spring.learn.replace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class TestMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("替换原有方法");
        return null;
    }

}
