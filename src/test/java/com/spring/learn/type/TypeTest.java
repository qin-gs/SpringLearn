package com.spring.learn.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ResolvableType;
import org.springframework.scripting.ScriptEvaluator;
import org.springframework.web.util.JavaScriptUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

@DisplayName("type")
public class TypeTest {

    @Test
    public void test1() {
        // 获取泛型类实现接口的实际类型信息
        ParameterizedType parameterizedType =
                (ParameterizedType) ABService.class.getGenericInterfaces()[0];
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(Arrays.toString(actualTypeArguments)); // [class com.spring.learn.type.A, class com.spring.learn.type.B]

        // 使用 spring 提供的 ResolvableType 获取泛型信息
        ResolvableType resolvableType = ResolvableType.forClass(ABService.class);
        Class<?> resolve = resolvableType.as(Service.class).getGeneric(0).resolve();
        System.out.println(resolve);


    }
}


interface Service<M, N> {
}

class ABService implements Service<A, B> {
}

class CDService implements Service<C, D> {
}

class A {
}

class B {
}

class C {
}

class D {
}