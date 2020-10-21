package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteElement(int key) {
        storage[key] = storage[size - 1];
    }

    @Override
    protected void saveElement(int key, Resume resume) {
        storage[size] = resume;
        System.out.println(resume.getUuid() + " добавлен(а) в массив");
    }
}
