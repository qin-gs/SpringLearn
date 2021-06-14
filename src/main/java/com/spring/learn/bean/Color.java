package com.spring.learn.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Color implements Serializable {
    private String name;

    private Red red;

    public Color() {
    }

    @Autowired
    public Color(Red red) {
        this.red = red;
    }

    // public Color(@Autowired Red red) {
    //     this.red = red;
    // }


    @Autowired
    public void setRed(Red red) {
        this.red = red;
    }
}
