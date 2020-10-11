package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int searchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doDelete(int key) {
        storage[key] = storage[size - 1];
    }

    @Override
    protected void doSave(int key, Resume resume) {
        storage[size] = resume;
        System.out.println(resume.getUuid() + " добавлен(а) в массив");
    }
}
