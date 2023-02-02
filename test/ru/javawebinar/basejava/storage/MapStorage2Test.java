package ru.javawebinar.basejava.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapStorage2Test extends AbstractStorageTest {

    public MapStorage2Test() {
        super(new MapStorage2());
    }

    @Override
    @Test
    public void saveOverflow() {

    }
}