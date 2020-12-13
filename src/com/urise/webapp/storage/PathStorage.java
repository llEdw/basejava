package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialization.Serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private Serialization serialization;

    protected PathStorage(String dir, Serialization serialization) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.serialization = serialization;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Path read error", directory.toFile().getName(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        String file = directory + "\\" + uuid;
        return Paths.get(file);
    }

    @Override
    protected void doUpdate(Path directory, Resume resume) {
        try {
            serialization.doWrite(resume, new BufferedOutputStream(new FileOutputStream(directory.toFile())));
        } catch (IOException e) {
            throw new StorageException("Path write error", directory.toFile().getName(), e);
        }
    }

    @Override
    protected boolean isExist(Path directory) {
        return Files.exists(directory);
    }

    @Override
    protected void doSave(Path directory, Resume resume) {
        try {
            Files.createFile(directory);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file ", directory.toFile().getName(), e);
        }
        doUpdate(directory, resume);
    }

    @Override
    protected Resume doGet(Path directory) {
        try {
            return serialization.doRead(new BufferedInputStream(new FileInputStream(directory.toFile())));
        } catch (IOException e) {
            throw new StorageException("Path read error", directory.toFile().getName(), e);
        }
    }

    @Override
    protected void doDelete(Path directory) {
        try {
            Files.delete(directory);
        } catch (IOException e) {
            throw new StorageException("Path delete error", directory.toFile().getName());
        }
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> list = new ArrayList<>();
        try {
            for (Object file : Files.list(directory).toArray()) {
                list.add(doGet((Path)file));
            }
        } catch (IOException e) {
            throw new StorageException("Error", directory.toFile().getName());
        }
        return list;
    }
}