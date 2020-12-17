package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStreamSerialization;

public class ObjectStreamSerializationPathStorageTest extends AbstractStorageTest {
    public ObjectStreamSerializationPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new ObjectStreamSerialization()));
    }
}