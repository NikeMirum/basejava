package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {
    public void update(Resume r) {
        SK index = getSearchKeyIfExist(r.getUuid());
        updateElement(index, r);
    }

    public void save(Resume r) {
        SK index = getSearchKeyIfNotExist(r.getUuid());
        addElement(index, r);
    }

    public void delete(String uuid) {
        SK index = getSearchKeyIfExist(uuid);
        deleteElement(index);
    }

    public Resume get(String uuid) {
        SK index = getSearchKeyIfExist(uuid);
        return getElement(index);
    }


    protected SK getSearchKeyIfExist(String uuid) {
        SK getSearchKey = getSearchKey(uuid);
        if (!isSearchKeyExist(getSearchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    protected SK getSearchKeyIfNotExist(String uuid) {
        SK getSearchKey = getSearchKey(uuid);
        if (isSearchKeyExist(getSearchKey)) {
            throw new ExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    protected abstract boolean isSearchKeyExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void updateElement(SK searchKey, Resume r);

    protected abstract void addElement(SK searchKey, Resume r);

    protected abstract void deleteElement(SK searchKey);

    protected abstract Resume getElement(SK searchKey);
}
