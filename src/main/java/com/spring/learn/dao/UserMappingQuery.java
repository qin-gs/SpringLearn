package com.spring.learn.dao;

import com.spring.learn.bean.User2;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UserMappingQuery extends MappingSqlQuery<User2> {

    public UserMappingQuery(DataSource dataSource) {
        super(dataSource, "select * from user");
        declareParameter(new SqlParameter("id", Types.INTEGER));
        compile();
    }

    @Override
    protected User2 mapRow(ResultSet rs, int rowNum) throws SQLException {
        User2 user = new User2();
        user.setId(rs.getString("id"));
        // ...其他字段
        return user;
    }

}
