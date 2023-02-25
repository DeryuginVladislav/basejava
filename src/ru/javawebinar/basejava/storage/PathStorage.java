package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serialization.SerializationStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    protected final Path directory;
    private final SerializationStrategy serializationStrategy;

    protected PathStorage(String dir, SerializationStrategy serializationStrategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        this.serializationStrategy = serializationStrategy;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        getStreamPaths().forEach(this::doDelete);
    }

    @Override
    public void doUpdate(Resume r, Path path) {
        try {
            serializationStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File write error", path.toFile().getName(), e);
        }
    }

    @Override
    public void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + path, path.toFile().getName(), e);
        }
        doUpdate(r, path);
    }

    @Override
    public Resume doGet(Path path) {
        try {
            return serializationStrategy.doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File read error", path.toFile().getName(), e);
        }
    }

    @Override
    public void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Delete error", path.toFile().getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list = new ArrayList<>();
        getStreamPaths().forEach(path -> list.add(doGet(path)));
        return list;
    }

    @Override
    public int size() {
        return (int) getStreamPaths().count();
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    private Stream<Path> getStreamPaths() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Open directory error", null, e);
        }
    }
}
