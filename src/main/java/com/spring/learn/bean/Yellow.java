package com.spring.learn.bean;

public class Yellow {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Yellow{" +
                "name='" + name + '\'' +
                '}';
    }
}
