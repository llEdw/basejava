package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    public Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
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
        storage.update(new Resume("dummy"));
    }

    @Test
    public void update() {
        storage.update(new Resume("uuid1"));
        storage.update(new Resume("uuid3"));
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExitFirstElement() {
        storage.save(new Resume("uuid1"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExitLastElement() {
        storage.save(new Resume("uuid3"));
    }

    @Test
    public void getAll() {
        Resume[] resumes1 = storage.getAll();
        Resume[] resumes2 = {new Resume("uuid1"), new Resume("uuid2"), new Resume("uuid3")};
        Assert.assertEquals(resumes2, resumes1);
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4"));
        storage.get("uuid4");
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        storage.delete("uuid3");
        storage.get("uuid2");
        Assert.assertEquals(1, storage.size());
    }

    @Test
    public void getFirstElement() {
        Assert.assertEquals(new Resume("uuid1"), storage.get("uuid1"));
    }

    @Test
    public void getLastElement() {
        Assert.assertEquals(new Resume("uuid3"), storage.get("uuid3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        try {
            for (int i = 0; i < 7; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}