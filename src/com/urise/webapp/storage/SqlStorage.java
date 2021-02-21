package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
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
                sqlHelper.transactionalExecute(conn -> {
                            Resume resume;
                            try (PreparedStatement ps = conn.prepareStatement("" +
                                    "    SELECT * FROM resume r" +
                                    " WHERE r.uuid =?")) {
                                ps.setString(1, uuid);
                                ResultSet rs = ps.executeQuery();
                                if (!rs.next()) {
                                    throw new NotExistStorageException(uuid);
                                }
                                resume = new Resume(uuid, rs.getString("full_name"));
                            }
                            try (PreparedStatement ps = conn.prepareStatement("" +
                                    "    SELECT * FROM contact c" +
                                    " WHERE c.resume_uuid =?")) {
                                ps.setString(1, uuid);
                                ResultSet rs = ps.executeQuery();
                                while (rs.next()) {
                                    if (rs.getString("type") != null) {
                                        addContacts(rs, resume);
                                        ps.addBatch();
                                    }
                                }
                            }
                            try (PreparedStatement ps = conn.prepareStatement("" +
                                    "    SELECT * FROM section s" +
                                    " WHERE s.resume_uuid =?")) {
                                ps.setString(1, uuid);
                                ResultSet rs = ps.executeQuery();
                                while (rs.next()) {
                                    String type = rs.getString("type");
                                    if (type != null) {
                                        addSections(rs, resume, type);
                                        ps.addBatch();
                                    }
                                }
                                ps.executeBatch();
                            }
                            return resume;
                        }
                );
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        sqlHelper.transactionalExecute(conn -> {
                    String uuid = resume.getUuid();
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name =? WHERE uuid =?;")) {
                        ps.setString(1, resume.getFullName());
                        ps.setString(2, uuid);
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(uuid);
                        }
                    }
                    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid =?;")) {
                        ps.setString(1, uuid);
                        ps.execute();
                    }
                    insertIntoContact(conn, resume);
                    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid =?;")) {
                        ps.setString(1, uuid);
                        ps.execute();
                    }
                    insertIntoSection(conn, resume);
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
                    insertIntoContact(conn, resume);
                    insertIntoSection(conn, resume);
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
        return
                sqlHelper.transactionalExecute(conn -> {
                            Map<String, Resume> map = new LinkedHashMap<>();
                            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                                ResultSet rs = ps.executeQuery();
                                while (rs.next()) {
                                    String uuid = rs.getString("uuid");
                                    map.put(uuid, new Resume(uuid, rs.getString("full_name")));
                                }
                            }
                            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                                ResultSet rs = ps.executeQuery();
                                while (rs.next()) {
                                    if (rs.getString("type") != null) {
                                        addContacts(rs, map.get(rs.getString("resume_uuid")));
                                    }
                                }
                            }
                            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                                ResultSet rs = ps.executeQuery();
                                while (rs.next()) {
                                    String type = rs.getString("type");
                                    if (type != null) {
                                        addSections(rs, map.get(rs.getString("resume_uuid")), type);
                                    }
                                }
                            }
                            return new ArrayList<>(map.values());
                        }
                );
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

    private void addSections(ResultSet rs, Resume resume, String type) throws SQLException {
        Section section = null;
        switch (type) {
            case "PERSONAL":
            case "OBJECTIVE":
                section = new TextSection(rs.getString("value"));
                break;
            case "ACHIEVEMENT":
            case "QUALIFICATIONS":
                List<String> items = Arrays.asList(rs.getString("value").split("_"));
                section = new ListSection(items);
                break;
        }
        SectionType sectionType = SectionType.valueOf(type);
        resume.addSection(sectionType, section);
    }

    private void insertIntoContact(Connection conn, Resume resume) throws SQLException {
        Map<ContactType, String> contacts = resume.getContacts();
        if (!contacts.isEmpty()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                for (Map.Entry<ContactType, String> e : contacts.entrySet()) {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, e.getValue());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void insertIntoSection(Connection conn, Resume resume) throws SQLException {
        Map<SectionType, Section> sections = resume.getSections();
        if (!sections.isEmpty()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
                for (Map.Entry<SectionType, Section> e : sections.entrySet()) {
                    ps.setString(1, resume.getUuid());
                    String key = e.getKey().name();
                    ps.setString(2, key);
                    if (key.equals("ACHIEVEMENT") || key.equals("QUALIFICATIONS")) {
                        String value = e.getValue().toString().replaceAll("\n, ", "\n_");
                        ps.setString(3, value.substring(1, value.length() - 1));
                    } else {
                        ps.setString(3, e.getValue().toString());
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }
}








