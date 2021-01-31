package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper;
    private static final Logger LOG = Logger.getLogger(SqlStorage.class.getName());

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.sqlHelperExecute("DELETE FROM resume", ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return
                sqlHelper.sqlHelperExecute("SELECT * FROM resume WHERE uuid =?", ps -> {
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
        String r = resume.getUuid();
        sqlHelper.sqlHelperExecute("UPDATE resume SET full_name =? WHERE uuid =?;", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, r);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r);
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        sqlHelper.sqlHelperExecute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        sqlHelper.sqlHelperExecute("DELETE FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
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
                sqlHelper.sqlHelperExecute("SELECT * FROM resume ORDER BY full_name, uuid", ps -> {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        list.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
                    }
                    return list;
                });
    }

    @Override
    public int size() {
        return
                sqlHelper.sqlHelperExecute("SELECT COUNT(*) FROM resume", ps -> {
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    return rs.getInt(1);
                });
    }
}







