package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Резюме " + resume.getUuid() + " уже есть в массиве");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Нет свободного места");
        } else {
            index = -(index + 1);
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
            System.out.println(resume.getUuid() + " добавлен(а) в массив");
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex((uuid));
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            size--;
            System.out.println("Резюме " + uuid + " удален(а)");
        } else {
            System.out.println("Резюме " + uuid + " нет в массиве");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}