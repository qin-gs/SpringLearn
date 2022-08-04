package com.spring.learn.valid;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 校验逻辑：
 * <pre>
 *  HttpEntityMethodProcessor: 解析 HttpEntity 类型的入参和返回值
 *  RequestResponseBodyMethodProcessor: 解析 @RequestBody 注解的入参 和 @ResponseBody 返回值
 *  RequestPartMethodArgumentResolver：解析 @RequestPart 注解的入参
 * </pre>
 * 以上三种请求下，@ControllerAdvice 注解的切面才会生效，其余情况入参和返回值不会对其产生作用
 * <p>
 *  <pre>
 *  RequestResponseBodyMethodProcessor 处理逻辑
 *      supportsParameter：只处理注解了 @ResponseBody 的方法参数
 *      resolveArgument：
 *          1. 从 request 中读取入参并使用消息转换器转换参数
 *              1.1 获取媒体类型、入参类型、请求方法
 *              1.2 循环匹配的消息转换器进行转换，判断有没有请求体
 *                  - 有的话循环调用 ControllerAdvice 切面 RequestBodyAdvice#beforeBodyRead (会先判断是否切当前调用的类)
 *                  - 调用转换器的 read 的方法，反序列化入参
 *                  - 循环调用 ControllerAdvice 切面 RequestBodyAdvice#afterBodyRead (会先判断是否切当前调用的类)
 *
 *                  - 没有的话调用 ControllerAdvice 切面 RequestBodyAdvice#handleEmptyBody (会先判断是否切当前调用的类)
 *          2. 获取数据绑定器
 *          3. 判断是否使用了 @Validated 或 @Valid 注解，进行校验
 *              获取参数上的注解，如果有 @Validated 或 @Valid 注解，则调用数据绑定器进行校验
 *          4. 判断下一个参数类型是不是 BindingResult，是的话将校验结果绑定到 BindingResult 中，不是的话报错(可以通过全局异常处理)
 *
 *  </pre>
 */
@Configuration
public class Config {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:/properties/valid.properties");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }
}
