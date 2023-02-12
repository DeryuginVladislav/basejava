package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume r, Integer index) {
        storage.set(index, r);
    }

    @Override
    public void doSave(Resume r, Integer index) {
        storage.add(r);
    }

    @Override
    public Resume doGet(Integer index) {
        return storage.get(index);
    }

    @Override
    public void doDelete(Integer index) {
        storage.remove((int) index);
    }

    @Override
    protected List<Resume> copyUnsortedPart() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Integer index) {
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
