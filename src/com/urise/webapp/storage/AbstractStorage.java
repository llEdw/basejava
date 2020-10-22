package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> {
        if (o1.getFullName() != (o2.getFullName())) {
            return o1.getFullName().compareTo(o2.getFullName());
        } else {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };

    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    protected abstract void doUpdate(Object searchKey, Resume resume);

    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    protected abstract void doSave(Object searchKey, Resume resume);

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    protected abstract void doDelete(Object searchKey);

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    protected abstract Resume doGet(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);

    public List<Resume> getAllSorted() {
        List<Resume> list = getList();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    protected abstract List<Resume> getList();
}
