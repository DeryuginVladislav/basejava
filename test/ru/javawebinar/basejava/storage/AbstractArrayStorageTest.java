package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_VALUE; i++) {
                storage.save(new Resume("Name" + i));
            }
        } catch (Exception e) {
            fail("overflow happened ahead of time");
        }
        storage.save(new Resume("overflow"));
    }
}