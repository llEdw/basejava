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
        saveUpdate(resume);
    }

    public void save(Resume resume) {
        saveUpdate(resume);
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                System.out.println("Резюме " + uuid + " удален(а)");
                return;
            }
        }
        System.out.println("Резюме " + uuid + " нет в массиве");
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

    public void saveUpdate(Resume resume) {
        StackTraceElement[] element = Thread.currentThread().getStackTrace();
        for (int i = 0; i < size; i++) {
            if (resume.equals(storage[i])) {
                if (element[element.length - 2].getMethodName().equals("save")) {
                    System.out.println("Резюме " + resume.getUuid() + " уже есть в массиве");
                    return;
                } else {
                    storage[i] = resume;
                    System.out.println("Резюме " + resume.getUuid() + " обновлено");
                    return;
                }
            }
        }
        if (element[element.length - 2].getMethodName().equals("save")) {
            if (size == storage.length) {
                System.out.println("Нет свободного места");
                return;
            }
            if (resume.getUuid() == null) {
                System.out.println("Вы не ввели значение. Повторите попытку");
            } else {
                storage[size] = resume;
                System.out.println("Резюме " + storage[size] + " добавлен(а) в массив");
                size++;
            }
        } else {
            System.out.println("Резюме " + resume.getUuid() + " не найдено");
        }
    }
}
