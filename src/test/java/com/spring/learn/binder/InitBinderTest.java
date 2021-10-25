package com.spring.learn.binder;

import com.spring.learn.formatter.DateFormatter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class InitBinderTest {

    /**
     * 没有返回值
     * 进行数据类型转换
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(sdf, true);
        binder.registerCustomEditor(Date.class, editor);

        // 4.2 之后推荐这种方式
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    /**
     * 绑定变量名和属性，参数封装类
     * 前端传值如下：
     * a.name=aaa
     * a.age=12
     * b.name=bbb
     * b.age=23
     */
    @InitBinder("userA")
    public void initBean(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
        binder.setDisallowedFields("*age"); // 设置不允许的字段
        binder.setAllowedFields("id"); // 设置允许的字段
        binder.setRequiredFields("bane"); // 设置必须的字段
    }

    /**
     * 将入参a放到Model中
     */
    public void save(@ModelAttribute("userA") Object a) {

    }

    /**
     * 对方法标注 @ModelAttribute 注解，在调用各个目标方法前都会去调用@ModelAttribute 标记的注解，
     * 允许我们在调用目标方法前操纵模型数据。
     * 入参的类型和目标方法处允许的入参类型一致
     * HttpServletRequest, HttpServletResponse, HttpSession, ModelAndView, Model, ModelMap
     * SpringMVC在调用方法之前会创建一个隐含的模型对象作为数据的存储容器
     */
    @ModelAttribute
    public void modelAttribute() {

    }
}
