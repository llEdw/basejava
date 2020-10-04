package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;

public class ListStorage extends AbstractStorage {
    private final ArrayList<Resume> LIST = new ArrayList<>();

    public void clear() {
        System.out.println("Список очищен, удалено " + LIST.size() + " элементов");
        LIST.clear();
    }

    @Override
    protected void updateElement(int index, Resume resume) {
        LIST.set(index, resume);
    }

    @Override
    protected int getIndex(Resume resume, String uuid) {
        return LIST.indexOf(resume);
    }

    @Override
    protected void saveElement(int index, Resume resume) {
        LIST.add(resume);
        System.out.println(resume.getUuid() + " добавлен в список");
    }

    @Override
    protected boolean checkLimit() {
        return false;
    }

    @Override
    protected void deleteElement(String uuid, int index) {
        Iterator<Resume> iterator = LIST.iterator();
        while (iterator.hasNext()) {
            Resume element = iterator.next();
            if (element.getUuid().equals(uuid)) {
                iterator.remove();
            }
        }
    }

    @Override
    protected Resume getElement(int index) {
        return LIST.get(index);
    }

    public int size() {
        return LIST.size();
    }

    public Resume[] getAll() {
        return LIST.toArray(new Resume[LIST.size()]);
    }
}
