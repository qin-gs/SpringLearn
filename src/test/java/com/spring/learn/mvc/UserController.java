package com.spring.learn.mvc;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 每个控制器对应一个逻辑处理
 */
public class UserController extends AbstractController implements LastModified {
    private long lastModified;

    /**
     * 重写方法，完成逻辑处理
     */
    @Override
    protected ModelAndView handleRequestInternal(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        User user1 = new User();
        user1.setUsername("qqq");
        user1.setAge(12);

        User user2 = new User();
        user2.setUsername("www");
        user2.setAge(34);
        List<User> users = Arrays.asList(user1, user2);

        // viewName: 视图组件的逻辑名称，视图解析器会使用该名称查找实际的view对象
        // modelName: 传递给视图的模型对象的名称
        // modelObject: 传递给视图的模型对象的值
        return new ModelAndView("userlist", "users", users);
    }

    /**
     * 内容改变时返回最新的修改时间
     */
    @Override
    public long getLastModified(HttpServletRequest request) {
        if (lastModified == 0L) {
            lastModified = System.currentTimeMillis();
        }
        return lastModified;
    }
}
