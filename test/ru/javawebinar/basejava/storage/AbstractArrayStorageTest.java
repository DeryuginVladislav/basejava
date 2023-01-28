package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";
    private static final Resume R_1;
    private static final Resume R_2;
    private static final Resume R_3;
    private static final Resume R_4;


    static {
        R_1 = new Resume(UUID_1);
        R_2 = new Resume(UUID_2);
        R_3 = new Resume(UUID_3);
        R_4 = new Resume(UUID_4);
    }

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R_1);
        storage.save(R_2);
        storage.save(R_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Resume[] resumes = new Resume[0];
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_2);
        storage.update(r);
        assertSame(r, storage.get(UUID_2));
    }

    @Test
    public void save() {
        storage.save(R_4);
        assertGet(R_4);
        assertSize(4);
    }

    @Test
    public void get() {
        assertGet(R_1);
        assertGet(R_2);
        assertGet(R_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(R_1.getUuid());
        assertSize(2);
        assertGet(R_1);
    }

    @Test
    public void getAll() {
        Resume[] expected = {R_1, R_2, R_3};
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);
        assertSize(3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(R_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_VALUE; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            fail("overflow happened ahead of time");
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(storage.get(resume.getUuid()), resume);
    }
}