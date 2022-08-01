package com.spring.learn.aop.declare;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DeclareAspect {

    /**
     * 指定满足指定表达式的类将自动实现某些接口
     */
    @DeclareParents(value = "com.spring.learn.bean.User", defaultImpl = VerifierImpl.class)
    public Verifier verifier;
}
