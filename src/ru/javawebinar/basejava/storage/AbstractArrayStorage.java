package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

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
    public void doUpdate(Resume r, Object index) {
        storage[(int) index] = r;
    }

    @Override
    public final void doSave(Resume r, Object index) {
        if (size >= STORAGE_VALUE) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r, (Integer) index);
            size++;
        }
    }

    @Override
    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    public final void doDelete(Object index) {
        deleteResume((Integer) index);
        size--;
    }

    public List<Resume> getAllSorted() {
        Resume[] resumes = Arrays.copyOf(storage, size);
        Arrays.sort(resumes,(r1, r2) -> {
            int result = r1.getFullName().compareTo(r2.getFullName());
            if (result != 0) {
                return result;
            } else {
                return r1.getUuid().compareTo(r2.getUuid());
            }
        });
        return Arrays.asList(resumes);
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract Integer getSearchKey(String uuid);
}