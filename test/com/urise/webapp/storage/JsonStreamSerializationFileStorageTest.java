package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.JsonStreamSerialization;

public class JsonStreamSerializationFileStorageTest extends AbstractStorageTest {

    public JsonStreamSerializationFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new JsonStreamSerialization()));
    }
}