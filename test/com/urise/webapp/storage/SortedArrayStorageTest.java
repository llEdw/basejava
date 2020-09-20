package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    AbstractArrayStorageTest sortedArrayStorageTest = new SortedArrayStorageTest(new SortedArrayStorage());

    public SortedArrayStorageTest(Storage storage) {
        super(storage);
    }
}