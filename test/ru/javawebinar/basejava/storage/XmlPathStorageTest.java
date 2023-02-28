package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.XmlStrategy;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new XmlStrategy()));
    }
}