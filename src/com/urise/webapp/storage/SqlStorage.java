package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        return sqlHelper.sqlHelperExecute("" +
                        "    SELECT * FROM resume r" +
                        " LEFT JOIN contact c" +
                        "        ON r.uuid = c.resume_uuid" +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContacts(rs, resume);
                    } while (rs.next());
                    return resume;
                });
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        String r = resume.getUuid();
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name =? WHERE uuid =?;")) {
                        ps.setString(1, resume.getFullName());
                        ps.setString(2, r);
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(r);
                        }
                    }
                    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid =?;")) {
                        ps.setString(1, r);
                        ps.execute();
                    }
                    inserIntoContact(conn, resume);
                    return null;
                }
        );
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?);")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    inserIntoContact(conn, resume);
                    return null;
                }
        );
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
        List<Resume> list = new ArrayList<>();
        return sqlHelper.sqlHelperExecute("" +
                        "    SELECT * FROM resume r" +
                        " LEFT JOIN contact c" +
                        "        ON r.uuid = c.resume_uuid" +
                        " ORDER BY full_name, uuid",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    String uuid = null;
                    Resume resume = null;
                    while (rs.next()) {
                        if (!rs.getString("uuid").equals(uuid)) {
                            uuid = rs.getString("uuid");
                            resume = new Resume(uuid, rs.getString("full_name"));
                            list.add(resume);

                        }
                        addContacts(rs, resume);
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

    private void addContacts(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        ContactType type = ContactType.valueOf(rs.getString("type"));
        resume.addContact(type, value);
    }

    private void inserIntoContact(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}







