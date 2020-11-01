package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> list = new ArrayList<>();

    public void clear() {
        System.out.println("Список очищен, удалено " + list.size() + " элементов");
        list.clear();
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        list.set(searchKey, resume);
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
    protected void doSave(Integer searchKey, Resume resume) {
        list.add(resume);
        System.out.println(resume.getUuid() + " добавлен в список");
    }

    @Override
    protected void doDelete(Integer searchKey) {
        list.remove((searchKey).intValue());
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return list.get(searchKey);
    }

    public int size() {
        return list.size();
    }

    @Override
    protected List<Resume> getList() {
        return list;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }


}
