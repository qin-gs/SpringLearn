package com.spring.learn.proxy.cglib;

import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Dispatcher;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.LazyLoader;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

public class EnhancerTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerTest.class);
        enhancer.setCallback(new MethodInterceptorImpl());

        EnhancerTest test = ((EnhancerTest) enhancer.create());
        // test.test();
        // test.test2();
        System.err.println(test);
    }

    public void test() {
        System.err.println("EnhancerTest test()");
    }

    public void test2() {
        System.err.println("test 2");
    }

    private static class MethodInterceptorImpl implements MethodInterceptor {

        /**
         * 所有的方法都会被拦截，需要按照method进行筛选
         */
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.err.println("before invoke " + method);
            // 如果用method调用，会再次进入拦截器，所以用proxy
            Object o = proxy.invokeSuper(obj, args);
            System.err.println("after invoke " + method);
            return o;
        }
    }

    public void callback() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerTest.class);

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.err.println("before invoke" + method);
                // 如果用method调用，会再次进入拦截器，所以用proxy
                Object o = proxy.invokeSuper(obj, args);
                System.err.println("after invoke" + method);
                return o;
            }
        });

        enhancer.setCallback(new NoOp() {
            // 什么都不处理
        });

        // 懒加载 当被增强的bean的方法第一次被调用的是时候，会触发回调，之后的调用都是对第一次返回bean的调用
        enhancer.setCallback(new LazyLoader() {
            @Override
            public Object loadObject() throws Exception {
                return null;
            }
        });

        // 类似与LazyLoader，但是每次进行方法调用都会触发回调
        enhancer.setCallback(new Dispatcher() {
            @Override
            public Object loadObject() throws Exception {
                return null;
            }
        });

        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        // 用于替换方法的返回值为回调方法的返回值，需要保证返回类型是兼容的，否则会出现转换异常
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                // 用这个方法的返回值替换原方法的值，需要保证两个值类型兼容
                return "replaced";
            }
        });

        // 实现一些定制化操作
        CallbackHelper helper = new CallbackHelper(EnhancerTest.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getReturnType().equals(void.class)) {
                    return new MethodInterceptor() {
                        @Override
                        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                            return null;
                        }
                    };
                } else if (method.getReturnType().equals(String.class)) {
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return null;
                        }
                    };
                } else {
                    return NoOp.INSTANCE;
                }
            }
        };
        enhancer.setCallbacks(helper.getCallbacks());

        EnhancerTest test = ((EnhancerTest) enhancer.create());
        test.test();
        System.out.println(test);
    }
}
