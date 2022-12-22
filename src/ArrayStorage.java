import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int length = size();
        for (int i = 0; i < length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        int nullIndex = 0;
        while (storage[nullIndex] != null) {
            nullIndex++;
        }
        storage[nullIndex] = r;
    }

    Resume get(String uuid) throws NullPointerException {
        try {
            int counter = 0;
            while (storage[counter].uuid != uuid) {
                counter++;
            }
            return storage[counter];
        } catch (NullPointerException e) {
            return null;
        }
    }

    void delete(String uuid) {
        int counter = 0;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = null;
                counter++;
                break;
            }
        }
        for (int i = --counter; i < size(); i++) {
            storage[i] = storage[i + 1];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                size++;
            }
        }
        return size;
    }
}