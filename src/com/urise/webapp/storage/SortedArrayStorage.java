package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, null);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_SAS_COMPARATOR);
    }

    private static final Comparator<Resume> RESUME_SAS_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected void deleteElement(int key) {
        System.arraycopy(storage, key + 1, storage, key, size - key - 1);
    }

    @Override
    protected void saveElement(int key, Resume resume) {
        key = -(key + 1);
        System.arraycopy(storage, key, storage, key + 1, size - key);
        storage[key] = resume;
        System.out.println(resume.getUuid() + " добавлен(а) в массив");
    }
}