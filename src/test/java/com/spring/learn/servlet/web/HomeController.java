package com.spring.learn.servlet.web;

import com.spring.learn.servlet.exception.MyException;
import com.spring.learn.servlet.pojo.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping("/test/{id}")
    public void test(@RequestParam(value = "max", defaultValue = "20") int max,
                     @PathVariable(value = "id") String id,
                     Model model) {

    }

    @RequestMapping("submit")
    public void form(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {

        }
    }

    /**
     * 处理当前controller中的指定异常
     */
    @ExceptionHandler(MyException.class)
    public String handlerException() {
        JdbcOperations jdbcOperations = new JdbcTemplate();
        jdbcOperations.queryForObject("select * from user", this::mapUser, "123");
        return "error";
    }

    private User mapUser(ResultSet rs, int row) throws SQLException {
        User user = new User();
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setAge(rs.getInt("age"));
        return user;
    }

    public void test() {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(new DelegatingDataSource());
        String insert = "insert into user values (:name, :password, :age)";
        Map<String, ? extends Serializable> map = Map.of("name", "qqq", "password", "www", "age", 12);
        template.update(insert, map);
    }

}
