package com.spring.learn.servlet;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 注册一个servlet
 */
public class MyServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // ServletRegistration.Dynamic dynamic = servletContext.addServlet("myServlet", MyServlet.class);
        // dynamic.addMapping("/custom/**"); // 映射servlet
        //
        // FilterRegistration.Dynamic filter = servletContext.addFilter("myFilter", MyFilter.class);
        // filter.addMappingForUrlPatterns(null, false, "/custom/**"); // 注册filter
    }
}
