package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume r, Object index) {
        storage.set((Integer) index, r);
    }

    @Override
    public void doSave(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    public Resume doGet(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    public void doDelete(Object index) {
        storage.remove((int) index);
    }

    @Override
    public List<Resume> getAllSorted() {
        storage.sort((r1, r2) -> {
            int result = r1.getFullName().compareTo(r2.getFullName());
            if (result != 0) {
                return result;
            } else {
                return r1.getUuid().compareTo(r2.getUuid());
            }
        });
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            Resume r = storage.get(i);
            if (r.getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
