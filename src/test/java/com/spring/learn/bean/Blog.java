package com.spring.learn.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 这个类会使用自定义命名策略
 */
@Component
public class Blog implements Serializable {
    private String id;
    private String title;
    private User2 author;
    private String body;
    private Date time;
    private List<Comment> comments;

    public static String staticField;

    public static String getStaticField() {
        return staticField;
    }

    public Blog() {
    }

    public Blog(String title) {
        this.title = title + "_constructor";
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public User2 getAuthor() {
        return this.author;
    }

    public String getBody() {
        return this.body;
    }

    public Date getTime() {
        return this.time;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(User2 author) {
        this.author = author;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", body='" + body + '\'' +
                ", time=" + time +
                ", comments=" + comments +
                '}';
    }
}
