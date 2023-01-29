package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume r, int index) {
        int insertionPoint = Math.abs(index + 1);
        if (storage[insertionPoint] != null) {
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1,size-insertionPoint);
        }
        storage[insertionPoint] = r;
    }

    @Override
    protected void deleteResume(int index) {
        storage[index] = null;
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
