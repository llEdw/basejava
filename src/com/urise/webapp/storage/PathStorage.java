package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serialization.Serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            getStreamList().forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int)getStreamList().count();
        } catch (IOException e) {
            throw new StorageException("Path read error", directory.toFile().getName(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Path directory, Resume resume) {
        try {
            serialization.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(directory)));
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
            return serialization.doRead(new BufferedInputStream(Files.newInputStream(directory)));
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
        try {
            return getStreamList().map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Path getList error", directory.getFileName().toString());
        }
    }

    private Stream<Path> getStreamList() throws IOException {
        return Files.list(directory);
    }
}