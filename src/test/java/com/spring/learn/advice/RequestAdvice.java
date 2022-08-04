package com.spring.learn.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * RequestBodyAdvice 通过 aop 实现的，仅对使用了 @RequestBody 的方法生效，所以 get 请求不会生效；
 * 处理逻辑在 RequestResponseBodyMethodProcessor#resolveArgument -> AbstractMessageConverterMethodArgumentResolver#readWithMessageConverters
 * <p>
 * 可以用来完成数据解密、数据转换等功能
 */
@RestControllerAdvice(basePackages = "com.spring.learn.advice")
public class RequestAdvice extends RequestBodyAdviceAdapter {

    /**
     * 可以通过注解等方式判断是否要进行解密
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
                                           Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        // 获取原生数据
        byte[] body = new byte[inputMessage.getBody().available()];
        inputMessage.getBody().read(body);
        // 进行解密
        // ...
        byte[] after = body;

        // 解密后构造新的流
        return new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(after);
            }

            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
                                MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // body 是 controller 的形参对象 (比如 User)
        // controller 中的方法
        Method method = parameter.getMethod();
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage,
                                  MethodParameter parameter, Type targetType,
                                  Class<? extends HttpMessageConverter<?>> converterType) {
        return super.handleEmptyBody(body, inputMessage, parameter, targetType, converterType);
    }
}
