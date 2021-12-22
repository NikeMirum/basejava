package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, (size - 1), null);
        size = 0;
    }

    public void update(Resume r) {
        if (indexOfResumeInStorage(r.getUuid()) != -1) {
            storage[indexOfResumeInStorage(r.getUuid())] = r;
        } else System.out.printf("ERROR: There is no similar Resume with uuid %s to update", r.getUuid());
    }

    public void save(Resume r) {
        if (indexOfResumeInStorage(r.getUuid()) == -1) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else System.out.println("ERROR: No space in ArrayStorage!");
        } else System.out.printf("ERROR: Resume with uuid %s already exist", r.getUuid());

    }

    public Resume get(String uuid) {
        if (indexOfResumeInStorage(uuid) != -1) {
            return storage[indexOfResumeInStorage(uuid)];
        }
        System.out.printf("ERROR: No Resume with uuid %s found", uuid);
        return null;
    }

    public void delete(String uuid) {
        if (indexOfResumeInStorage(uuid) != -1) {
            storage[indexOfResumeInStorage(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else System.out.printf("ERROR: No Resume with uuid %s found", uuid);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int indexOfResumeInStorage(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}