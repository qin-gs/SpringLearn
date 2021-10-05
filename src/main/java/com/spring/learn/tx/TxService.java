package com.spring.learn.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class TxService {

    @Autowired
    private DataSource dataSource;

    @Transactional(rollbackFor = RuntimeException.class)
    public void getTest() {
        throw new UnsupportedOperationException();
    }
}
