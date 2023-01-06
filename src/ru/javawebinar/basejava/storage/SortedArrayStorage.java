package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insert(Resume r) {
        int insertionPoint = Math.abs(Arrays.binarySearch(storage, 0, size, r) + 1);
        storage[insertionPoint] = r;
        size++;
    }

    @Override
    protected void erase(int index) {
        storage[index] = null;
        size--;
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
