package com.spring.learn.session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * 在多个请求之间共用Model的属性数据，将属性暂存到HttpSession
 */
@Controller
@SessionAttributes(value = "user", types = Object.class)
public class SessionAttributeTest {

    @ModelAttribute("user")
    public Object addUser() {
        return new Object();
    }

    /**
     * 会将入参放到Model中
     */
    @RequestMapping("put")
    public void put(@ModelAttribute("user") Object user) {

    }

    @RequestMapping("get")
    public void get(ModelMap map, SessionStatus status) {
        Object user = map.get("user");
        status.setComplete(); // 清楚该控制器类的所有会话属性
    }
}
