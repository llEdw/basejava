package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.DataStreamSerialization;

public class DataStreamSerializationFileStorageTest extends AbstractStorageTest {

    public DataStreamSerializationFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new DataStreamSerialization()));
    }
}