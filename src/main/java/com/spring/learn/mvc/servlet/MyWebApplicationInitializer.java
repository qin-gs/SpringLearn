package com.spring.learn.mvc.servlet;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * servlet3.0 之后的 javaee servlet配置
 * <p>
 * WebApplicationInitializer 是 springmvc提供的配置，确保基于代码的配置能自动初始化servlet容器
 * <p>
 * 之前需要将 DispatcherServlet 需要声明到web.xml
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {

        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        // 所有以app开头的请求都映射到DispatcherServlet
        registration.addMapping("/app/*");
    }
}
