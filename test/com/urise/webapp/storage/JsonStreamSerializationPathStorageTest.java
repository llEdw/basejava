package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.JsonStreamSerialization;

public class JsonStreamSerializationPathStorageTest extends AbstractStorageTest {
    public JsonStreamSerializationPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new JsonStreamSerialization()));
    }
}