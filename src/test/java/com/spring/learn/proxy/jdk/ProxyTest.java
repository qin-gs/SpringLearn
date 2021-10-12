package com.spring.learn.proxy.jdk;

import org.junit.jupiter.api.Test;

public class ProxyTest {
    @Test
    public void test() {
        // 创建目标对象
        UserService service = new UserServiceImpl();

        MyInvocationHandler handler = new MyInvocationHandler(service);
        // 根据目标对象生成代理对象
        UserService proxy = (UserService) handler.getProxy();
        // 调用代理对象的方法
        proxy.add();
    }
}
