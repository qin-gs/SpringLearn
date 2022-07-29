package com.spring.learn.valid.group;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @RequestMapping("save")
    public void save(@Validated({Second.class}) User user, BindingResult result) {

    }
}
