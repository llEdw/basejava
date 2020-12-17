package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        ObjectStreamSerializationFileStorageTest.class,
        ObjectStreamSerializationPathStorageTest.class,
        XmlStreamSerializationFileStorageTest.class,
        XmlStreamSerializationPathStorageTest.class,
        JsonStreamSerializationFileStorageTest.class,
        JsonStreamSerializationPathStorageTest.class,
        DataStreamSerializationFileStorageTest.class,
        DataStreamSerializationPathStorageTest.class
})
public class AllStorageTest {
}
