package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
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
        return (Resume) resumeSearchKey;
    }

    @Override
    public void doDelete(Object resumeSearchKey) {
        Resume r = (Resume) resumeSearchKey;
        storage.remove(r.getUuid());
    }

    @Override
    public List<Resume> copyUnsortedPart() {
        return new ArrayList<>(storage.values());
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
        return resume != null;
    }
}
