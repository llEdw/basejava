package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    public void clear() {
        System.out.println("Список очищен, удалено " + map.size() + " элементов");
        map.clear();
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        map.put(resume.getUuid(), resume);
        System.out.println(resume.getUuid() + " добавлен в карту");
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    public int size() {
        return map.size();
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> list = new ArrayList<>(map.values());
        return list;
    }
}
