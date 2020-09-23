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
    private static final Resume R1 = new Resume("1");
    private static final Resume R2 = new Resume("2");
    private static final Resume R3 = new Resume("3");
    private static final Resume R0 = new Resume("dummy");

    public AbstractArrayStorageTest(Storage storage) {
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
        storage.update(R1);
        storage.get("1");
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void updateLastElement() {
        storage.update(R3);
        storage.get("3");
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
        storage.save(new Resume("4"));
        storage.get("4");
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] actualResumes = storage.getAll();
        Resume[] expectedResumes = {R1, R2, R3};
        Assert.assertArrayEquals(actualResumes, expectedResumes);
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

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        int storageSize = storage.size();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT - storageSize; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}