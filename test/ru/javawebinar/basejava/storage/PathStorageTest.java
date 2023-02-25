package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.ObjectStreamStrategy;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamStrategy()));
    }
}