package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedList;
import java.util.ListIterator;

public class ListStorage extends AbstractStorage {
    private final LinkedList<Resume> storage = new LinkedList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Resume r, Object searchKey) {
        int index = storage.indexOf(r);
        storage.set(index, r);
    }

    @Override
    public void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return storage.get(getIndex(searchKey));
    }

    @Override
    public void doDelete(Object searchKey) {
        storage.remove(getIndex(searchKey));
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object resume) {
        return storage.contains(resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        ListIterator<Resume> iterator = storage.listIterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        return null;
    }

    private int getIndex(Object searchKey) {
        int index = storage.indexOf(searchKey);
        return index;
    }
}
