package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    public void clear() {
        System.out.println("Список очищен, удалено " + map.size() + " элементов");
        map.clear();
    }

    @Override
    protected void updateElement(int key, Resume resume) {
        map.put(intToString(key), resume);
    }

    @Override
    protected int searchKey(String uuid) {
        if (map.get(uuid) == null) {
            return -1;
        } else {
            return stringToInt(uuid);
        }
    }

    private int stringToInt(String uuid) {
        return Integer.parseInt(uuid);
    }

    private String intToString(int key) {
        return String.valueOf(key);
    }

    @Override
    protected void saveElement(int key, Resume resume) {
        map.put(resume.getUuid(), resume);
        System.out.println(resume.getUuid() + " добавлен в карту");
    }

    @Override
    protected void deleteElement(int key) {
        map.remove(intToString(key));
    }

    @Override
    protected Resume getElement(int key) {
        return map.get(intToString(key));
    }

    public int size() {
        return map.size();
    }

    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }
}
