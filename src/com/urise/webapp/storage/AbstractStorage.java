package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    public void update(Resume resume) {
        int index = getIndex(resume, resume.getUuid());
        if (index >= 0) {
            updateElement(index, resume);
            System.out.println("Резюме " + resume.getUuid() + " обновлено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract void updateElement(int index, Resume resume);

    public void save(Resume resume) {
        int index = getIndex(resume, resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (checkLimit()) {
            throw new StorageException("Нет свободного места", resume.getUuid());
        } else {
            saveElement(index, resume);
        }
    }

    protected abstract boolean checkLimit();

    protected abstract void saveElement(int index, Resume resume);

    public void delete(Resume resume, String uuid) {
        int index = getIndex(resume, uuid);
        if (index >= 0) {
            deleteElement(uuid, index);
            System.out.println("Резюме " + uuid + " удален(а)");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void deleteElement(String uuid, int index);

    public Resume get(Resume resume, String uuid) {
        int index = getIndex(resume, uuid);
        if (index >= 0) {
            System.out.println("Найдено резюме ");
            return getElement(index);
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract Resume getElement(int index);

    protected abstract int getIndex(Resume resume, String uuid);
}
