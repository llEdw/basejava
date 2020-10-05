package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            updateElement(index, resume);
            System.out.println("Резюме " + resume.getUuid() + " обновлено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract void updateElement(int index, Resume resume);

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveElement(index, resume);
        }
    }

    protected abstract void saveElement(int index, Resume resume);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(index);
            System.out.println("Резюме " + uuid + " удален(а)");
        }
    }

    protected abstract void deleteElement(int index);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Найдено резюме ");
            return getElement(index);
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract Resume getElement(int index);

    protected abstract int getIndex(String uuid);
}
