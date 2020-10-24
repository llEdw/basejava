package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 20;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void doSave(Object index, Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Нет свободного места", resume.getUuid());
        } else {
            saveElement((Integer) index, resume);
            size++;
        }
    }

    protected abstract void saveElement(int index, Resume resume);

    @Override
    protected void doDelete(Object index) {
        deleteElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void deleteElement(int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        System.out.println("Массив очищен, удалено " + size + " элементов");
        size = 0;
    }

    @Override
    protected List<Resume> getList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void doUpdate(Object index, Resume resume) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected abstract Integer getSearchKey(String uuid);
}
