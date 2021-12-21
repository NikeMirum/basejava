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
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume r) {
        if (checkIfResumePresentByUuid(r.getUuid())) {
            storage[indexOfResumeInStorage(r.getUuid())] = r;
        } else System.out.println("ERROR: There is no Resume to update");
    }

    public void save(Resume r) {
        if (!checkIfResumePresentByUuid(r.getUuid())) {
            if (size < 10000) {
                storage[size] = r;
                size++;
            } else System.out.println("ERROR: No space in ArrayStorage!");
        } else System.out.println("ERROR: Resume with this uuid already exist");

    }

    public Resume get(String uuid) {
        if (checkIfResumePresentByUuid(uuid)) {
            return storage[indexOfResumeInStorage(uuid)];
        }
        System.out.println("ERROR: No such Resume found");
        return null;
    }

    public void delete(String uuid) {
        int delIndex = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                delIndex = i;
                break;
            }
        }
        if (delIndex != -1) {
            for (int i = delIndex; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
            size--;
        } else System.out.println("ERROR: No Resume with certain ID found");
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

    public boolean checkIfResumePresentByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public Integer indexOfResumeInStorage(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}