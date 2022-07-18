package com.spring.learn.filter;

/**
 * DelegatingFilterProxy，通过代理的方式来实现 filter，可以在 filter 中注入 spring 中的 bean，
 * spring 会在 context 中查找名字为 filter-name 的 bean，封装带 proxy 中，
 * 如果需要调用 init，destroy 方法，需要将 targetFilterLifecycle 置为 true
 * <pre>
 * {@code
 *     <filter>
 *         <filter-name>myFilter</filter-name>
 *         <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 *         <init-param>
 *             <param-name>targetFilterLifecycle</param-name>
 *             <param-vale>true</param-value>
 *         </init-param>
 *     </filter>
 *     <filter-mapping>
 *         <filter-name></filter-name>
 *         <url-pattern>/*</url-pattern>
 *     </filter-mapping>
 * }
 * </pre>
 */
public class FilterProxyTest {
}
