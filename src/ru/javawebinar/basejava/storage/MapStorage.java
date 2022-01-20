package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> storage = new HashMap();

    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = storage.values().toArray(new Resume[0]);
        Arrays.sort(resumes);
        return resumes;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isIndexOrKeyExist(String key) {
        return storage.containsKey(key);
    }

    @Override
    protected String getIndexOrKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateElement(String key, Resume r) {
        storage.put(key, r);
    }

    @Override
    protected void addElement(String key, Resume r) {
        storage.put(key, r);
    }

    @Override
    protected void deleteElement(String key) {
        storage.remove(key);
    }

    @Override
    protected Resume getElement(String key) {
        return storage.get(key);
    }

}
