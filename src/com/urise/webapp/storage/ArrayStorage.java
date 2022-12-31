package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;
    int index;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void update(Resume r) {
        if (isExist(r.uuid)) {
            storage[index] = r;
        } else {
            System.out.println("Error: резюме c uuid = " + r.uuid + " не существует");
        }
    }

    public void save(Resume r) {
        if (size != storage.length) {
            if (!isExist(r.uuid)) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Error: резюме c uuid = " + r.uuid + " уже существует");
            }
        } else {
            System.out.println("Error: массив заполнен");
        }
    }

    public Resume get(String uuid) {
        if (isExist(uuid)) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        if (isExist(uuid)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error: резюме c uuid = " + uuid + " не существует");
        }
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

    private boolean isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                index = i;
                return true;
            }
        }
        return false;
    }
}