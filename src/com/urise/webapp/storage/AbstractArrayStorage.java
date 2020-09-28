package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        System.out.println("Массив очищен, удалено " + size + " элементов");
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            throw new StorageException("Нет свободного места", resume.getUuid());
        } else {
            saveElement(index, resume);
            System.out.println(resume.getUuid() + " добавлен(а) в массив");
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex((uuid));
        if (index >= 0) {
            deleteElement(index);
            storage[size - 1] = null;
            size--;
            System.out.println("Резюме " + uuid + " удален(а)");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Найдено резюме ");
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(int index, Resume resume);
}
