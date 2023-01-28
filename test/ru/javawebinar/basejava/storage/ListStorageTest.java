package ru.javawebinar.basejava.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListStorageTest extends AbstractArrayStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Test
    public void saveOverflow() {

    }
}