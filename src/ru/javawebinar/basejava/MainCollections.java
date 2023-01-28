package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume R_1 = new Resume(UUID_1);
    private static final Resume R_2 = new Resume(UUID_2);
    private static final Resume R_3 = new Resume(UUID_3);
    private static final Resume R_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(R_1);
        collection.add(R_2);
        collection.add(R_3);
        collection.add(R_4);

        for (Resume r : collection) {
            System.out.println(r);
            if (r.getUuid().equals(UUID_1)) {
//                collection.remove(r);
            }
        }
        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (r.getUuid().equals(UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection);

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, R_1);
        map.put(UUID_2, R_2);
        map.put(UUID_3, R_3);

        // Bad!
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
