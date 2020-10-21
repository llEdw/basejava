package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    public void clear() {
        System.out.println("Список очищен, удалено " + list.size() + " элементов");
        list.clear();
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        list.set((Integer) searchKey, resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        list.add(resume);
        System.out.println(resume.getUuid() + " добавлен в список");
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    public int size() {
        return list.size();
    }

    @Override
    public List<Resume> getAllSorted() {
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }


}
