package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    public void update(Resume resume) {
        int key = searchKey(resume.getUuid());
        if (key >= 0) {
            updateElement(key, resume);
            System.out.println("Резюме " + resume.getUuid() + " обновлено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract void updateElement(int key, Resume resume);

    public void save(Resume resume) {
        int key = searchKey(resume.getUuid());
        if (key >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveElement(key, resume);
        }
    }

    protected abstract void saveElement(int key, Resume resume);

    public void delete(String uuid) {
        int key = searchKey(uuid);
        if (key < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(key);
            System.out.println("Резюме " + uuid + " удален(а)");
        }
    }

    protected abstract void deleteElement(int key);

    public Resume get(String uuid) {
        int key = searchKey(uuid);
        if (key >= 0) {
            System.out.println("Найдено резюме ");
            return getElement(key);
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract Resume getElement(int key);

    protected abstract int searchKey(String uuid);
}
