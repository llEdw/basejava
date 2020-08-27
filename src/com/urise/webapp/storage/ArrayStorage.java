package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int arraySize;

    public void clear() {
        Arrays.fill(storage, null);
        System.out.println("Массив очищен");
        arraySize = 0;
    }

    public void update(Resume r) {
        for (int i = 0; i < arraySize; i++) {
            if (r.equals(storage[i])) {
                System.out.println("Обновлено");
                return;
            }
        }
        System.out.println("Резюме не найдено");
    }

    public void save(Resume r) {
        for (int i = 0; i < arraySize; i++) {
            if (r.equals(storage[i])) {
                System.out.println("Такое резюме уже есть в массиве");
                return;
            }
        }
        if (arraySize == 10000) {
            System.out.println("Нет свободного места");
            return;
        }
        if (r.getUuid() == null) {
            System.out.println("Вы не ввели значение. Повторите попытку");
        } else {
            storage[arraySize] = r;
            System.out.println(storage[arraySize] + " добавлен(а) в массив");
            arraySize++;
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < arraySize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < arraySize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[arraySize - 1];
                storage[arraySize - 1] = null;
                arraySize--;
                System.out.println(uuid + " удален(а)");
                return;
            }
        }
        System.out.println("Такого резюме нет в массиве");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, arraySize);
    }

    public int size() {
        return arraySize;
    }
}
