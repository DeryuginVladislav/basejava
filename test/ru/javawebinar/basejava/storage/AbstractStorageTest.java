package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.javawebinar.basejava.ResumeTestData.createResume;

public abstract class AbstractStorageTest {
    protected final Storage storage;
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
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
        R_1 = createResume(UUID_1, "Name1");
        R_2 = createResume(UUID_2, "Name2");
        R_3 = createResume(UUID_3, "Name3");
        R_4 = createResume(UUID_4, "Name4");
    }

    protected AbstractStorageTest(Storage storage) {
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
        List<Resume> list = new ArrayList<>();
        assertEquals(list, storage.getAllSorted());
    }

    @Test
    public void update() {
        Resume r = createResume(UUID_2, "newName");
        r.addContact(ContactType.MOBILE,"89290309687");
        storage.update(r);
        assertEquals(r, storage.get(UUID_2));
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
    public void getAllSorted() {
        List<Resume> expected = Arrays.asList(R_1, R_2, R_3);
        List<Resume> actual = storage.getAllSorted();
        assertEquals(expected, actual);
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
        storage.save(createResume(UUID_2, "fullName"));
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