package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.DataStreamStrategy;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new DataStreamStrategy()));
    }
}