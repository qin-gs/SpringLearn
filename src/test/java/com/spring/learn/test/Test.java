package com.spring.learn.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        // 测试数据
        List<User> users = new ArrayList<>();
        users.add(new User("张三", "武汉"));
        users.add(new User("李四", "北京"));
        users.add(new User("王五", "北京"));
        users.add(new User("赵六", "上海"));

        //  按city分组
        List<List<User>> lus = new ArrayList<>(
                users.stream()
                        .collect(Collectors.groupingBy(User::getCity))
                        .values()
        );
        // 输出
        System.out.println(lus);
    }

}

class User {
    private String name;

    private String city;

    public User() {
    }

    public User(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}