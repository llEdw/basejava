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
    protected void updateElement(int index, Resume resume) {
        map.put(intToString(index), resume);
    }

    @Override
    protected int getIndex(String uuid) {
        for (Map.Entry<String, Resume> pair : map.entrySet()) {
            if (pair.getKey().equals(uuid)) {
                return stringToInt(pair.getKey());
            }
        }
        return -1;
    }

    private int stringToInt(String uuid) {
        return Integer.parseInt(uuid);
    }

    private String intToString(int index) {
        return String.valueOf(index);
    }

    @Override
    protected void saveElement(int index, Resume resume) {
        map.put(resume.getUuid(), resume);
        System.out.println(resume.getUuid() + " добавлен в карту");
    }

    @Override
    protected void deleteElement(int index) {
        map.remove(intToString(index));
    }

    @Override
    protected Resume getElement(int index) {
        return map.get(intToString(index));
    }

    public int size() {
        return map.size();
    }

    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }
}
