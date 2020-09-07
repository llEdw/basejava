package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        System.out.println("Массив очищен, удалено " + size + " элементов");
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено");
        } else {
            System.out.println("Резюме " + resume.getUuid() + " не найдено");
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Резюме " + resume.getUuid() + " уже есть в массиве");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Нет свободного места");
        } else {
            saveElement(index, resume);
            System.out.println(resume.getUuid() + " добавлен(а) в массив");
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex((uuid));
        if (index >= 0) {
            deleteElement(index);
            size--;
            System.out.println("Резюме " + uuid + " удален(а)");
        } else {
            System.out.println("Резюме " + uuid + " нет в массиве");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Найдено резюме ");
            return storage[index];
        } else {
            System.out.println("Резюме " + uuid + " не найдено ");
            return null;
        }
    }

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(int index, Resume resume);
}
