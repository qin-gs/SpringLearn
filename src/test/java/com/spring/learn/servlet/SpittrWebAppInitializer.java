package com.spring.learn.servlet;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

/**
 * 扩展{@code AbstractAnnotationConfigDispatcherServletInitializer}的接口会自动配置dispatcher-servlet和spring应用上下文
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 用来配置{@code ContextLoaderListener}应用上下文中的bean
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 用来配置{@code DispatcherServlet}应用上下中的bean
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 将一个或多个路劲映射到{@link org.springframework.web.servlet.DispatcherServlet}
     * '/'表明这是应用默认的servlet，会处理所有请求
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 将dispatcherServlet注册到servlet容器中之后，会调用改方法
     * 可以对DispatcherServlet进行额外配置
     */
    @Override
    protected void customizeRegistration(javax.servlet.ServletRegistration.Dynamic registration) {
        // 启用multipart请求
        registration.setLoadOnStartup(1);
        registration.setInitParameter("", "");
        // 配置生成文件的临时目录，文件大小
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads", 2 * 1024 * 1024, 4 * 1024 * 1024, 0));
    }

    /**
     * 给DispatcherServlet添加filter，没有必要添加映射路径
     */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{};
    }
}
