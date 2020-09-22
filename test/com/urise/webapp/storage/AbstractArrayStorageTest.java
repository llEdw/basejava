package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final Resume r1 = new Resume("1");
    private static final Resume r2 = new Resume("2");
    private static final Resume r3 = new Resume("3");
    private static final Resume r0 = new Resume("dummy");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
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
        storage.update(r0);
    }

    @Test
    public void updateFirstElement() {
        storage.update(r1);
        storage.get("1");
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void updateLastElement() {
        storage.update(r3);
        storage.get("3");
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistFirstElement() {
        storage.save(r1);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistLastElement() {
        storage.save(r3);
    }

    @Test
    public void save() {
        storage.save(new Resume("4"));
        storage.get("4");
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumes1 = storage.getAll();
        Resume[] resumes2 = {r1, r2, r3};
        Assert.assertArrayEquals(resumes1, resumes2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void deleteFirstElement() {
        storage.delete("1");
        storage.get("2");
        storage.get("3");
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void deleteLastElement() {
        storage.delete("3");
        storage.get("1");
        storage.get("2");
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getFirstElement() {
        Assert.assertEquals(r1, storage.get("1"));
    }

    @Test
    public void getLastElement() {
        Assert.assertEquals(r3, storage.get("3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT - 3; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}