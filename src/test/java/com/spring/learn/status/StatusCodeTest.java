package com.spring.learn.status;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class StatusCodeTest {

    /**
     * 改变这个方法的返回状态码
     * 如果设置了reason，方法不会执行
     */
    @RequestMapping("test")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String test() {
        return "";
    }
}
