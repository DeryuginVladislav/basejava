package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_VALUE = 10000;
    protected Resume[] storage = new Resume[STORAGE_VALUE];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Resume r, Object searchKey) {
        storage[(int) searchKey] = r;
    }

    @Override
    public final void doSave(Resume r, Object searchKey) {
        if (size >= STORAGE_VALUE) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r, (int) searchKey);
            size++;
        }
    }

    @Override
    public Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    public final void doDelete(Object searchKey) {
        deleteResume((int) searchKey);
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract Integer getSearchKey(String uuid);
}