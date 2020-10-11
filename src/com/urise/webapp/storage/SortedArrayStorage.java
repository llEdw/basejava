package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int searchKey(String uuid) {
        Resume key = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, key);
    }

    @Override
    protected void doDelete(int key) {
        System.arraycopy(storage, key + 1, storage, key, size - key - 1);
    }

    @Override
    protected void doSave(int key, Resume resume) {
        key = -(key + 1);
        System.arraycopy(storage, key, storage, key + 1, size - key);
        storage[key] = resume;
        System.out.println(resume.getUuid() + " добавлен(а) в массив");
    }
}