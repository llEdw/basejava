package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStreamSerialization;

public class ObjectStreamSerializationFileStorageTest extends AbstractStorageTest {

    public ObjectStreamSerializationFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerialization()));
    }
}