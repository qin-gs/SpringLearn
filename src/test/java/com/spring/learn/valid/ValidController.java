package com.spring.learn.valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ValidController {

    public String save(@Valid PersonForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        return "success";
    }
}
