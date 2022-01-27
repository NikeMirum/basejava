package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    public void update(Resume r) {
        SK searchKey = getSearchKeyIfExist(r.getUuid());
        updateElement(searchKey, r);
    }

    public void save(Resume r) {
        SK searchKey = getSearchKeyIfNotExist(r.getUuid());
        addElement(searchKey, r);
    }

    public void delete(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        deleteElement(searchKey);
    }

    public Resume get(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        return getElement(searchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumeList = getAllElements();
        return resumeList.stream().sorted().toList();
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

    protected abstract List<Resume> getAllElements();
}
