package com.spring.learn.jsckson;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.learn.jdbc.service.UserService;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("getUser")
    @JsonView(User.WithoutPasswordView.class)
    public User getUser() {
        return new User();
    }
}

class User {

    public interface WithoutPasswordView {
    }

    /**
     * 子接口会展示自己注解的属性和其父接口注解的属性
     */
    public interface WithPasswordView extends WithoutPasswordView {
    }

    private String id;
    private String userName;
    private String password;

    @JsonView(WithoutPasswordView.class)
    public String getId() {
        return id;
    }

    @JsonView(WithoutPasswordView.class)
    public String getUserName() {
        return userName;
    }

    @JsonView(WithPasswordView.class)
    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
