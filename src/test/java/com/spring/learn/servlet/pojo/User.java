package com.spring.learn.servlet.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    @NotNull
    private String name;
    @NotNull
    @Size(min = 8, max = 32)
    private String password;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
