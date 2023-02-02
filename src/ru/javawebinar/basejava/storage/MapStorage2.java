package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage2 extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume r, Object resumeSearchKey) {
        Resume rSearchKey = (Resume) resumeSearchKey;
        storage.put(rSearchKey.getUuid(), r);
    }

    @Override
    public void doSave(Resume r, Object resumeSearchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public Resume doGet(Object resumeSearchKey) {
        Resume r = (Resume) resumeSearchKey;
        return storage.get(r.getUuid());
    }

    @Override
    public void doDelete(Object resumeSearchKey) {
        Resume r = (Resume) resumeSearchKey;
        storage.remove(r.getUuid());
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(storage.values());
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
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object resume) {
        return storage.containsValue(resume);
    }
}
