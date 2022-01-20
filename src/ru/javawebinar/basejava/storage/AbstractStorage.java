package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<IK> implements Storage {
    public void update(Resume r) {
        IK index = getIndexOrKeyIfExist(r.getUuid());
        updateElement(index, r);
    }

    public void save(Resume r) {
        IK index = getIndexOrKeyIfNotExist(r.getUuid());
        addElement(index, r);
    }

    public void delete(String uuid) {
        IK index = getIndexOrKeyIfExist(uuid);
        deleteElement(index);
    }

    public Resume get(String uuid) {
        IK index = getIndexOrKeyIfExist(uuid);
        return getElement(index);
    }


    protected IK getIndexOrKeyIfExist(String uuid) {
        IK getIndexOrKey = getIndexOrKey(uuid);
        if (!isIndexOrKeyExist(getIndexOrKey)) {
            throw new NotExistStorageException(uuid);
        }
        return getIndexOrKey(uuid);
    }

    protected IK getIndexOrKeyIfNotExist(String uuid) {
        IK getIndexOrKey = getIndexOrKey(uuid);
        if (isIndexOrKeyExist(getIndexOrKey)) {
            throw new ExistStorageException(uuid);
        }
        return getIndexOrKey(uuid);
    }

    protected abstract boolean isIndexOrKeyExist(IK indexOrKey);

    protected abstract IK getIndexOrKey(String uuid);

    protected abstract void updateElement(IK indexOrKey, Resume r);

    protected abstract void addElement(IK indexOrKey, Resume r);

    protected abstract void deleteElement(IK indexOrKey);

    protected abstract Resume getElement(IK indexOrKey);
}
