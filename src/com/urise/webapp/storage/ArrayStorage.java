package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

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
        if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Резюме " + resume.getUuid() + " уже есть в массиве");
            return;
        }
        if (size == storage.length) {
            System.out.println("Нет свободного места");
            return;
        }
        if (resume.getUuid() == null) {
            System.out.println("Вы не ввели значение. Повторите попытку");
        } else {
            storage[size] = resume;
            System.out.println(storage[size] + " добавлен(а) в массив");
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Найдено резюме ");
            return storage[index];
        } else {
            System.out.println("Резюме не найдено ");
            return null;
        }
    }

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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
