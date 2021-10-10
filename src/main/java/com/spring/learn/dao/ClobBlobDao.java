package com.spring.learn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClobBlobDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final LobHandler lobHandler = new DefaultLobHandler();

    public void test() throws IOException {
        final File blobIn = new File("spring2004.jpg");
        final InputStream blobIs = new FileInputStream(blobIn);
        final File clobIn = new File("large.txt");
        final InputStream clobIs = new FileInputStream(clobIn);
        final InputStreamReader clobReader = new InputStreamReader(clobIs);

        // 插入 clob, blob 类型的数据
        jdbcTemplate.execute(
                "INSERT INTO lob_table (id, a_clob, a_blob) VALUES (?, ?, ?)",
                new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                    @Override
                    protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
                        ps.setLong(1, 1L);
                        lobCreator.setClobAsCharacterStream(ps, 2, clobReader, (int) clobIn.length());
                        lobCreator.setBlobAsBinaryStream(ps, 3, blobIs, (int) blobIn.length());
                    }
                }
        );

        blobIs.close();
        clobReader.close();

        // 获取 clob, blob 类型的数据
        List<Map<String, Object>> l = jdbcTemplate.query("select id, a_clob, a_blob from lob_table",
                new RowMapper<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                        Map<String, Object> results = new HashMap<String, Object>();
                        String clobText = lobHandler.getClobAsString(rs, "a_clob");
                        results.put("CLOB", clobText);
                        byte[] blobBytes = lobHandler.getBlobAsBytes(rs, "a_blob");
                        results.put("BLOB", blobBytes);
                        return results;
                    }
                });
    }
}
