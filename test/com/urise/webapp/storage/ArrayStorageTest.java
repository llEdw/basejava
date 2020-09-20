package com.urise.webapp.storage;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    AbstractArrayStorageTest ArrayStorageTest = new ArrayStorageTest(new ArrayStorage());

    public ArrayStorageTest(Storage storage) {
        super(storage);
    }
}