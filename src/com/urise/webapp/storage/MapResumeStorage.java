package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> map = new HashMap<>();

    public void clear() {
        System.out.println("Список очищен, удалено " + map.size() + " элементов");
        map.clear();
    }

    @Override
    protected void doUpdate(Resume searchKey, Resume resume) {
        map.put((searchKey).getUuid(), resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume searchKey, Resume resume) {
        map.put(resume.getUuid(), resume);
        System.out.println(resume.getUuid() + " добавлен в карту");
    }

    @Override
    protected void doDelete(Resume searchKey) {
        map.remove((searchKey).getUuid());
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return searchKey;
    }

    public int size() {
        return map.size();
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(map.values());
    }
}
