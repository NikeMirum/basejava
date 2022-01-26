package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUuidStorage extends AbstractStorage<String> {
    private Map<String, Resume> storage = new TreeMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isSearchKeyExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateElement(String searchKey, Resume r) {
        storage.put(searchKey, r);
    }

    @Override
    protected void addElement(String searchKey, Resume r) {
        storage.put(searchKey, r);
    }

    @Override
    protected void deleteElement(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume getElement(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected List<Resume> getAllElements() {
        return storage.values().stream().toList();
    }

}
