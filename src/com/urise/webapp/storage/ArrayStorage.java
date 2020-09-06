package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Резюме " + resume.getUuid() + " уже есть в массиве");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Нет свободного места");
        } else {
            storage[size] = resume;
            System.out.println(storage[size] + " добавлен(а) в массив");
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex((uuid));
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Резюме " + uuid + " удален(а)");
        } else {
            System.out.println("Резюме " + uuid + " нет в массиве");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
