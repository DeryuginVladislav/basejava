package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
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
    public void update(Resume r) {
        if (!storage.contains(r)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            int index = storage.indexOf(r);
            storage.set(index, r);
        }
    }

    @Override
    public void save(Resume r) {
        if (storage.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            storage.add(r);
        }
    }

    @Override
    public Resume get(String uuid) {
        return searchResume(uuid);
    }

    @Override
    public void delete(String uuid) {
        storage.remove(searchResume(uuid));
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    private Resume searchResume(String uuid) {
        ListIterator<Resume> iterator = storage.listIterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        throw new NotExistStorageException(uuid);
    }
}
