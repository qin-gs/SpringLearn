package com.spring.learn.bean;

import java.util.List;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private int age;
    private Book book;
    private List<String> list;
    private Map<String, Object> map;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User(String username, String password, int age, List<String> list, Map<String, Object> map) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.list = list;
        this.map = map;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", book=" + book +
                ", list=" + list +
                ", map=" + map +
                '}';
    }

    public void showMe() {
        System.out.println("i am a user");
    }
}
