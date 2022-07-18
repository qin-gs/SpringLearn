package com.spring.learn.holder;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * RequestContextListener：web 服务器收到的每次请求都会通知该监听器
 */
public class RequestHolderTest {

    public void test() {
        // 获取当前请求的 request
        // 要使用 RequestContextHolder 需要配置 RequestContextListener 监听器
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
