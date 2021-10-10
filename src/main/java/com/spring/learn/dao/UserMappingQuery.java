package com.spring.learn.dao;

import com.spring.learn.bean.User;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UserMappingQuery extends MappingSqlQuery<User> {

    public UserMappingQuery(DataSource dataSource) {
        super(dataSource, "select * from user");
        declareParameter(new SqlParameter("id", Types.INTEGER));
        compile();
    }

    @Override
    protected User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        // ...其他字段
        return user;
    }

}
