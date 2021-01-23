package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.BlockSql;
import com.urise.webapp.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    private static final Logger LOG = Logger.getLogger(SqlStorage.class.getName());

    @Override
    public void clear() {
        sqlHelperExecute("DELETE FROM resume", connectionFactory, ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return
                sqlHelperExecute("SELECT * FROM resume WHERE uuid =?", connectionFactory, ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    return new Resume(uuid, rs.getString("full_name"));
                });
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        sqlHelperExecute("UPDATE resume SET full_name =? WHERE uuid =?;", connectionFactory, ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            ps.execute();
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        sqlHelperExecute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", connectionFactory, ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            try {
                ps.execute();
            } catch (SQLException e) {
                throw new ExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        sqlHelperExecute("DELETE FROM resume WHERE uuid =?", connectionFactory, ps -> {
            ps.setString(1, uuid);
            ps.execute();
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        ArrayList<Resume> list = new ArrayList<>();
        return
                sqlHelperExecute("SELECT * FROM resume ORDER BY full_name", connectionFactory, ps -> {
                    ps.execute();
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        list.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name").trim()));
                    }
                    return list;
                });
    }

    @Override
    public int size() {
        return
                sqlHelperExecute("SELECT COUNT(*) FROM resume", connectionFactory, ps -> {
                    ps.execute();
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException("Size");
                    }
                    return rs.getInt(1);
                });
    }

    private <T> T sqlHelperExecute(String sql, ConnectionFactory connectionFactory, BlockSql<T> blockSql) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return blockSql.sqlExecute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}







