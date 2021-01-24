package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.urise.webapp.ResumeTestData.fillResume;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    Storage storage;
    private static final String UUID1 = UUID.randomUUID().toString();
    private static final String UUID2 = UUID.randomUUID().toString();
    private static final String UUID3 = UUID.randomUUID().toString();
    private static final String UUID0 = UUID.randomUUID().toString();
    private static final String UUID4 = UUID.randomUUID().toString();

    private static final Resume R1 = fillResume(UUID1, "c");
    private static final Resume R2 = fillResume(UUID2, "a");
    private static final Resume R3 = fillResume(UUID3, "b");
    private static final Resume R0 = fillResume(UUID0, "e");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(R0);
    }

    @Test
    public void updateFirstElement() {
        Resume r5 = fillResume(UUID1, "f");
        storage.update(r5);
        Assert.assertEquals(r5, storage.get(UUID1));
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void updateLastElement() {
        Resume r6 = fillResume(UUID3, "g");
        storage.update(r6);
        Assert.assertEquals(r6, storage.get(UUID3));
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistFirstElement() {
        storage.save(R1);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistLastElement() {
        storage.save(R3);
    }

    @Test
    public void save() {
        Resume r4 = fillResume(UUID4, "h");
        storage.save(r4);
        Assert.assertEquals(r4, storage.get(UUID4));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedResumes = Arrays.asList(R2, R3, R1);
        Assert.assertEquals(expectedResumes, storage.getAllSorted());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID0);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteFirstElement() {
        storage.delete(UUID1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteLastElement() {
        storage.delete(UUID3);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID3);
    }

    @Test
    public void getFirstElement() {
        Assert.assertEquals(R1, storage.get(UUID1));
    }

    @Test
    public void getLastElement() {
        Assert.assertEquals(R3, storage.get(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID0);
    }
}