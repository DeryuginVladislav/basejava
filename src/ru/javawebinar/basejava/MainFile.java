package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) throws IOException {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Рекурсивный обход
        System.out.println("\n\nРекурсивный обход:");
        MainFile.printList("D:\\Users\\vladd\\basejava", 0);

    }

    private static void printList(String path, int depth) throws IOException {
        String indent = new String(new char[depth]).replace("\0", "  ");

        File dir = new File(path);
        File[] fileList = dir.listFiles();
        for (File f : fileList) {
            if (f.isDirectory()) {
                System.out.println(indent + f.getPath());
                printList(f.getCanonicalPath(), depth + 1);
            } else {
                System.out.println(indent + f.getName());
            }
        }
    }
}
