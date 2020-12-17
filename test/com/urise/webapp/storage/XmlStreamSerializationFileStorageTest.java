package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.XmlStreamSerialization;

public class XmlStreamSerializationFileStorageTest extends AbstractStorageTest {

    public XmlStreamSerializationFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new XmlStreamSerialization()));
    }
}