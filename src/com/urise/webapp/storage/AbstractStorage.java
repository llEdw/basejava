package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractStorage implements Storage {
    private final ArrayList<Resume> LIST = new ArrayList<>();

    @Override
    public void clear() {
        LIST.clear();
        System.out.println("Список очищен, удалено " + LIST.size() + " элементов");
    }

    @Override
    public void update(Resume resume) {
        if (LIST.contains(resume)) {
            LIST.set(LIST.indexOf(resume), resume);
            System.out.println("Резюме " + resume.getUuid() + " обновлено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        if (LIST.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            LIST.add(resume);
            System.out.println(resume.getUuid() + " добавлен в список");
        }
    }

    @Override
    public void delete(String uuid) {
        Iterator<Resume> iterator = LIST.iterator();
        while (iterator.hasNext()) {
            Resume element = iterator.next();
            if (element.getUuid().equals(uuid)) {
                iterator.remove();
                System.out.println("Резюме " + uuid + " удален(а)");
                return;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public int size() {
        return LIST.size();
    }

    @Override
    public Resume get(String uuid) {
        for (int i = 0; i < LIST.size(); i++) {
            if (LIST.get(i).getUuid().equals(uuid)) {
                System.out.println("Найдено резюме ");
                return LIST.get(i);
            }
        }
        throw new NotExistStorageException(uuid);
    }

    public Resume[] getAll() {
        return LIST.toArray(new Resume[LIST.size()]);
    }
}
