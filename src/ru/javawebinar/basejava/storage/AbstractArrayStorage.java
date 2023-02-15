package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_VALUE = 10000;
    protected Resume[] storage = new Resume[STORAGE_VALUE];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Resume r, Integer index) {
        storage[(int) index] = r;
    }

    @Override
    public final void doSave(Resume r, Integer index) {
        if (size >= STORAGE_VALUE) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r, index);
            size++;
        }
    }

    @Override
    public Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    public final void doDelete(Integer index) {
        deleteResume(index);
        size--;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract Integer getSearchKey(String uuid);
}