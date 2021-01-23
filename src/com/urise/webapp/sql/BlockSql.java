package com.urise.webapp.sql;


import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface BlockSql<T> {
    T sqlExecute(PreparedStatement ps) throws SQLException;
}
