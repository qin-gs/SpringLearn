// package com.spring.learn.mvc;
//
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerExceptionResolver;
// import org.springframework.web.servlet.ModelAndView;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// @Component
// public class ExceptionHandler implements HandlerExceptionResolver {
// 	@Override
// 	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
// 		request.setAttribute("exception", ex.toString());
// 		request.setAttribute("exceptionStack", ex);
// 		return new ModelAndView("error/exception");
// 	}
// }
