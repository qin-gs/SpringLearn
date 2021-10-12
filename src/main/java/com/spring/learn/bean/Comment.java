package com.spring.learn.bean;

import java.io.Serializable;

public class Comment implements Serializable {
    private String id;
    private String blogId;
    private String body;
    private User2 user;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User2 getUser() {
        return user;
    }

    public void setUser(User2 user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", blogId='" + blogId + '\'' +
                ", body='" + body + '\'' +
                ", user=" + user +
                ", content='" + content + '\'' +
                '}';
    }
}
