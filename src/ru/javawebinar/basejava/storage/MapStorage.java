package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    public void doSave(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    public Resume doGet(Object uuid) {
        return storage.get(uuid);
    }

    @Override
    public void doDelete(Object uuid) {
        storage.remove(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>( storage.values());
        list.sort((r1, r2) -> {
            int result = r1.getFullName().compareTo(r2.getFullName());
            if (result != 0) {
                return result;
            } else {
                return r1.getUuid().compareTo(r2.getUuid());
            }
        });
        return list;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid);
    }
}
