package com.spring.learn.aop;

import org.springframework.stereotype.Component;

@Component
public class AopMethod {


    public int testAop(int i, int j) {
        return i + j;
    }
}
