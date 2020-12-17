package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.DataStreamSerialization;

public class DataStreamSerializationPathStorageTest extends AbstractStorageTest {
    public DataStreamSerializationPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new DataStreamSerialization()));
    }
}