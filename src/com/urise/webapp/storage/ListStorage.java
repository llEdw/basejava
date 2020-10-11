package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> list = new ArrayList<>();

    public void clear() {
        System.out.println("Список очищен, удалено " + list.size() + " элементов");
        list.clear();
    }

    @Override
    protected void updateElement(int key, Resume resume) {
        list.set(key, resume);
    }

    @Override
    protected int searchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveElement(int key, Resume resume) {
        list.add(resume);
        System.out.println(resume.getUuid() + " добавлен в список");
    }

    @Override
    protected void deleteElement(int key) {
        list.remove(key);
    }

    @Override
    protected Resume getElement(int key) {
        return list.get(key);
    }

    public int size() {
        return list.size();
    }

    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }
}
