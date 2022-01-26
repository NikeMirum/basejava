package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> storage = new TreeMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isSearchKeyExist(Resume searchKey) {
        return storage.containsValue(searchKey);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return new Resume(uuid, "dummy");
    }

    @Override
    protected void updateElement(Resume searchKey, Resume r) {
        storage.put(searchKey.getUuid(), r);
    }

    @Override
    protected void addElement(Resume searchKey, Resume r) {
        storage.put(searchKey.getUuid(), r);
    }

    @Override
    protected void deleteElement(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    @Override
    protected Resume getElement(Resume searchKey) {
        return storage.get(searchKey.getUuid());
    }

    @Override
    protected List<Resume> getAllElements() {
        return storage.values().stream().toList();
    }

}
