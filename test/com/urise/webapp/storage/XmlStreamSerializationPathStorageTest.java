package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.XmlStreamSerialization;

public class XmlStreamSerializationPathStorageTest extends AbstractStorageTest {
    public XmlStreamSerializationPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new XmlStreamSerialization()));
    }
}