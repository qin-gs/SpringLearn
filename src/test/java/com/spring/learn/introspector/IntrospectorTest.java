package com.spring.learn.introspector;

import org.junit.jupiter.api.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * IntrospectorCleanupListener 在销毁 ServletContext 的时候清空对应缓存。
 * 在 Servlet3.0 规范之前，监听器的调用是随机的，而从 Servlet3.0 开始，监听器的调用顺序是根据其在 web.xml 中配置的顺序，
 * 并且实现 ServletContextListener 的监听器，contextInitialized 方法调用顺序是按照在 web.xml 中配置的顺序正序依次执行，
 * 而 contextDestroyed 方法的调用顺序是按照在 web.xml 中配置的顺序逆序依次执行。
 * 所以，如果 IntrospectorCleanupListener 被配置成了第一个 listener，那么它的 contextDestroyed 方法将最后一个执行，将发挥最有效的清除作用；
 * 而如果不是，那么可能会残留未被清除的缓存。
 */
public class IntrospectorTest {

    @Test
    public void test() throws IntrospectionException {
        // getBeanInfo 将得到的对象存储到了 ThreadGroupContext，是不会被回收的
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        MethodDescriptor[] descriptors = beanInfo.getMethodDescriptors();
        for (MethodDescriptor descriptor : descriptors) {
            System.out.println(descriptor);
        }
        User user = new User();
        Arrays.stream(descriptors).filter(descriptor -> descriptor.getName().equals("setAge"))
                .findFirst().ifPresent(descriptor -> {
                    try {
                        descriptor.getMethod().invoke(user, 12);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
        System.out.println("user = " + user);

        // 清空缓存
        Introspector.flushCaches();
    }
}
