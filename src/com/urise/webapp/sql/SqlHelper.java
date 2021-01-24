package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public static ConnectionFactory connectionFactory = null;

    public static <T> T sqlHelperExecute(String sql, BlockSql<T> blockSql) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return blockSql.sqlExecute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
