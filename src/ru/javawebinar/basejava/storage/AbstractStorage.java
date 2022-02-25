package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class AbstractStorage<SK> implements Storage {

    //    protected final Logger LOG = Logger.getLogger(getClass().getName());
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = getSearchKeyIfExist(r.getUuid());
        updateElement(searchKey, r);
    }

    public void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = getSearchKeyIfNotExist(r.getUuid());
        addElement(searchKey, r);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getSearchKeyIfExist(uuid);
        deleteElement(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getSearchKeyIfExist(uuid);
        return getElement(searchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resumeList = getAllElements();
        return resumeList.stream().sorted().collect(Collectors.toList());
    }


    protected SK getSearchKeyIfExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isSearchKeyExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected SK getSearchKeyIfNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isSearchKeyExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isSearchKeyExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void updateElement(SK searchKey, Resume r);

    protected abstract void addElement(SK searchKey, Resume r);

    protected abstract void deleteElement(SK searchKey);

    protected abstract Resume getElement(SK searchKey);

    protected abstract List<Resume> getAllElements();
}
