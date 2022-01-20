package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        // https://stackoverflow.com/a/2745351
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return storage.indexOf(searchKey);
    }

    @Override
    protected void updateElement(Integer index, Resume r) {
        storage.set(index, r);
    }

    @Override
    public void addElement(Integer index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void deleteElement(Integer index) {
        storage.remove(index.intValue());
    }

    @Override
    protected Resume getElement(Integer index) {
        return storage.get(index);
    }

    @Override
    protected boolean isSearchKeyExist(Integer index) {
        return index != -1;
    }
}
