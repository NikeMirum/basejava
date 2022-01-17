package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
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
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return storage.indexOf(searchKey);
    }

    @Override
    protected void updateElement(int index, Resume r) {
        storage.set(index, r);
    }


    public void addElement(Resume r, int index) {
        storage.add(r);
    }

    @Override
    protected void deleteElement(int index) {
        storage.remove(index);
    }

    @Override
    protected Resume getElement(int index) {
        return storage.get(index);
    }
}
