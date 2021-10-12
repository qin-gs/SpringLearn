package com.spring.learn.binder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 对方法标注 @ModelAttribute 注解，
 * 在调用各个目标方法前都会去调用@ModelAttribute 标记的注解，
 * 允许我们在调用目标方法前操纵模型数据。
 * 入参的类型和目标方法处允许的入参类型一致
 * HttpServletRequest, HttpServletResponse, HttpSession, ModelAndView, Model, ModelMap
 * <br>
 * 处理流程
 * 1. SpringMVC在调用方法之前在请求线程中自动创建一个隐含的模型对象作为数据的存储容器
 * 2. 调用所有表中的@ModelAttribute的方法，将方法返回值添加到模型中
 * 3. 查看session中是否存在@SessionAttributes("xxx")中指定的xxx属性；如果有，添加到模型中，已存在则覆盖
 * 4. 对标注了@ModelAttribute方法的入参进行处理
 * 1. 如果模型含有xxx的属性，填充该对象并赋值给入参
 * 2. 如果xxx是会话属性(@SessionAttributes中包含)，尝试从会话中获取并填充对象赋值给入参；如果找不到抛出异常(HttpSessionRequiredException)
 * 3. 如果模型中不含有该属性，也不是会话属性，就创建一个对象并填充赋值给入参
 * <br>
 * 注解中方法上
 * 注解中参数上
 * 注解在返回值上
 */
@Controller
public class ModelAttributeTest {

    /**
     * 方法返回void时，一般入参中使用Model参数
     */
    @ModelAttribute
    public void modelAttribute(Model model) {
        model.addAttribute("name", "value");
    }

    /**
     * 方法有返回值时，使用注解的value属性只在model中的名称
     * 如果不指定，默认是返回类型的首字母小写
     */
    @ModelAttribute("bean")
    public String modelAttribute() {
        return "bean";
    }

    /**
     * 以下两个注解同时使用时，返回值不是视图的名称，而是Model属性的值
     * 视图名称是@RequestMapping中指定的值(url.jsp)
     */
    @RequestMapping("url")
    @ModelAttribute("bean")
    public String modelAttribute(@RequestParam String id) {
        return "bean";
    }

    /**
     * 注解在方法的参数上时
     * 在Model中查找key(bean)对应的对象：
     * 如果存在就作为入参传入；
     * 如果不存在检查查当前类是否被@SessionAttributes注解修饰
     * 如果使用了该注解，并且session中存在该key对象，则取出来传进去
     * 如果不存在抛出异常
     * 如果没有被修饰或注解中不包含该key，就使用反射创建一个对象
     */
    @RequestMapping("url")
    public void modelAttribute(@ModelAttribute("bean") Object bean) {

    }

    /**
     * 注解在返回值上，将返回值添加到模型对象中
     */
    @RequestMapping("url")
    public @ModelAttribute("bean")
    Object modelAttribute(int id) {
        return new Object();
    }
}
