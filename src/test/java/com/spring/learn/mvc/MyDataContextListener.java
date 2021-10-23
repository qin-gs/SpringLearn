package com.spring.learn.mvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自定义一个ServletContextListener
 * 在系统启动时添加自定义的属性，在全局范围内都可以调用
 * 在web.xml中注册之后可以在jsp中调用
 * String myData = (String) getServletContext().getAttribute("myData");
 */
public class MyDataContextListener implements ServletContextListener {

	private ServletContext context = null;

	public MyDataContextListener() {
	}

	/**
	 * 该方法在ServletContext启动之后被调用，并准备好处理客户端请求
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		this.context = servletContextEvent.getServletContext();
		// 可以在其中实现自己的逻辑
		this.context.setAttribute("myData", "this is my data");
	}

	/**
	 * 该方法在ServletContext将关闭时调用
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		this.context = null;
	}
}
