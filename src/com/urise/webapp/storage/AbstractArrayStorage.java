package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 20;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void saveElement(int key, Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Нет свободного места", resume.getUuid());
        }
        doSave(key, resume);
        size++;
    }

    protected abstract void doSave(int key, Resume resume);

    @Override
    protected void deleteElement(int key) {
        doDelete(key);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void doDelete(int key);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        System.out.println("Массив очищен, удалено " + size + " элементов");
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected Resume getElement(int key) {
        return storage[key];
    }

    @Override
    protected void updateElement(int key, Resume resume) {
        storage[key] = resume;
    }
}
