package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";
    private static final Resume R_1 = new Resume(UUID_1);
    private static final Resume R_2 = new Resume(UUID_2);
    private static final Resume R_3 = new Resume(UUID_3);
    private static final Resume R_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
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
        Resume[] resumes = new Resume[3];
        Assert.assertTrue(resumes.equals(storage.getAll()));
    }

    @Test
    public void update() {
        Resume r = new Resume("uuid2");
        storage.update(r);
        Assert.assertSame(r, storage.get("uuid2"));
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
        Resume[] resumes = storage.getAll();
        Assert.assertTrue(expected.equals(resumes));
        assertSize(3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
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
            for (int i = 0; i < 10000; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail("overflow happened ahead of time");
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
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(storage.get(resume.getUuid()), resume);
    }
}