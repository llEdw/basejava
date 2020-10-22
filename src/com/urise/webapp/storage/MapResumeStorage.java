package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    public void clear() {
        System.out.println("Список очищен, удалено " + map.size() + " элементов");
        map.clear();
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        map.put(((Resume) searchKey).getUuid(), resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        map.put(resume.getUuid(), resume);
        System.out.println(resume.getUuid() + " добавлен в карту");
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(((Resume) searchKey).getUuid());
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
