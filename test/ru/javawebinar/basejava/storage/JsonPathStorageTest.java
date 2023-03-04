package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.JsonStrategy;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new JsonStrategy()));
    }
}