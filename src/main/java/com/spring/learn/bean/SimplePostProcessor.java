package com.spring.learn.bean;

public class SimplePostProcessor {
    private String url;
    private String password;
    private String username;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "SimplePostProcessor{" +
                "url='" + url + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
