package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_VALUE = 10000;
    protected Resume[] storage = new Resume[STORAGE_VALUE];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Error: резюме c uuid = " + r.getUuid() + " не существует");
        } else {
            storage[index] = r;
        }
    }

    public final void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > 0) {
            System.out.println("Error: резюме c uuid = " + r.getUuid() + " уже существует");
        } else if (size >= STORAGE_VALUE) {
            System.out.println("Error: массив переполнен");
        } else {
            insert(r);
        }
    }

    protected abstract void insert(Resume r);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Error: резюме c uuid = " + uuid + " не существует");
            return null;
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Error: резюме c uuid = " + uuid + " не существует");
        } else {
            erase(index);
        }
    }

    protected abstract void erase(int index);

    protected abstract int getIndex(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}