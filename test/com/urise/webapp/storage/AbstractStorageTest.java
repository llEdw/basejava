package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final Resume R1 = new Resume("1");
    private static final Resume R2 = new Resume("2");
    private static final Resume R3 = new Resume("3");
    private static final Resume R0 = new Resume("dummy");

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
        Resume r5 = new Resume("1");
        storage.update(r5);
        Assert.assertEquals(r5, storage.get("1"));
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void updateLastElement() {
        Resume r6 = new Resume("3");
        storage.update(r6);
        Assert.assertEquals(r6, storage.get("3"));
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
        Resume r4 = new Resume("4");
        storage.save(r4);
        Assert.assertEquals(r4, storage.get("4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] actualResumes = storage.getAll();
        Resume[] expectedResumes = {R1, R2, R3};
        Assert.assertArrayEquals(expectedResumes, actualResumes);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteFirstElement() {
        storage.delete("1");
        Assert.assertEquals(2, storage.size());
        storage.get("1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteLastElement() {
        storage.delete("3");
        Assert.assertEquals(2, storage.size());
        storage.get("3");
    }

    @Test
    public void getFirstElement() {
        Assert.assertEquals(R1, storage.get("1"));
    }

    @Test
    public void getLastElement() {
        Assert.assertEquals(R3, storage.get("3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}