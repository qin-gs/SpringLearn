package com.spring.learn.binder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

import java.util.Date;
import java.util.Map;

/**
 * DataBinder 数据绑定功能
 * <pre>
 *  1. 把属性值 PropertyValues 绑定到 target 上（bind()方法，依赖于 PropertyAccessor 实现）
 *  2. 提供校验的能力：提供了 public 方法 validate 对各个属性使用 Validator 执行校验
 *  3. 提供了注册属性编辑器 (PropertyEditor) 和对类型进行转换的能力 (TypeConverter)
 * </pre>
 * <p>
 * <pre>
 *      WebDataBinder：把 web 请求的参数绑定到 JavaBean，添加 _ ! 开头的默认值处理，把 MultipartFile 绑定到 JavaBean 某个属性上
 *      ServletRequestDataBinder：从 ServletRequest 中绑定数据到 JavaBean
 *      ExtendedServletRequestDataBinder：处理 url 中的参数绑定到 @PathVariable 上
 *      WebExchangeDataBinder：spring5.0 新增，对 Reactive 编程的 Mono 提供数据绑定
 *      MapDataBinder：spring-data 相关，处理 target 是 Map 的情况
 * </pre>
 * <p>
 * 可以通过实现 PropertyEditor 自定义数据绑定
 * <p>
 * WebBindInitializer 接口，实现全局属性编辑器注册 (ConfigurableWebBindingInitializer, WebDataBinderFactory, InitBinderDataBinderFactory)
 * 注解 @InitBinder，实现类级别属性编辑器注册
 */
public class BinderTest {

    @Test
    public void dataBinder() throws BindException {
        User user = new User();
        DataBinder binder = new DataBinder(user, "user");

        MutablePropertyValues pv = new MutablePropertyValues();
        pv.add("username", "aaa");
        pv.add("age", "12");

        binder.bind(pv);

        Map<?, ?> close = binder.close();
        System.out.println(user);
        System.out.println(close);
    }

    @Test
    public void webDataBinder() throws BindException {
        User user = new User();
        DataBinder binder = new WebDataBinder(user, "user");

        MutablePropertyValues pv = new MutablePropertyValues();
        pv.add("!username", "aaa");
        pv.add("age", "12");
        // 可以使用 ! 指定默认值，指定了确切的之后默认值不会生效
        pv.add("!age", "18");

        binder.bind(pv);
        Map<?, ?> close = binder.close();
        System.out.println(user);
        System.out.println(close);
    }

    @Test
    public void servletDataBinder() throws BindException {
        User user = new User();
        ServletRequestDataBinder binder = new ServletRequestDataBinder(user, "user");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("username", "aaa");
        request.addParameter("age", "12");
        request.addParameter("list", "1", "2", "3");
        request.addParameter("map['key1']", "value1");
        request.addParameter("map['key2']", "value2");

        binder.bind(request);
        System.out.println(user);
    }

    @Test
    public void dateBinder() {
        User user = new User();

        DataBinder binder = new DataBinder(user, "user");
        binder.registerCustomEditor(Date.class, new DatePropertyEditor());

        MutablePropertyValues pv = new MutablePropertyValues();
        pv.add("username", "aaa");
        pv.add("age", "12");
        pv.add("start", new Date());
        pv.add("end", "2022-08-04");
        pv.add("time", "Sat Fri 4 14:37:58 CST 2022");

        binder.bind(pv);
        System.out.println(user);

    }
}
