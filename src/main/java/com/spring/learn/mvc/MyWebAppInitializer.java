package com.spring.learn.mvc;

import com.spring.learn.mvc.servlet.AppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 用代码配置一个 WebApplicationContext
 * 每个 DispatcherServlet 都有自己的 WebApplicationContext(里面是独有的bean比如 Controller, ViewResolver, HandlerMapping),
 * 它继承自 root WebApplicationContext(里面有一些共享的bean比如 Services, Repositories)
 * <p>
 * WebApplicationContext中特殊的bean如下，在DispatcherServlet.properties中有默认配置(可以覆盖)
 * <pre>
 *     HandlerMapping, HandlerAdapter, HandlerExceptionResolver
 *     ViewResolver, LocalResolver & LocalContextResolver, ThemeResolver
 *     MultipartResolver, FlashMapResolver
 * </pre>
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/app/*"};
    }
}
