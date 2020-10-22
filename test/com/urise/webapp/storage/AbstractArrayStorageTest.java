package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        int storageSize = storage.size();
        try {
            for (int i = storageSize; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("p"));
            }
        } catch (StorageException e) {
            Assert.fail("Фактический размер массива меньше предполагаемого");
        }
        storage.save(new Resume("p"));
    }
}