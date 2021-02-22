package com.urise.webapp.storage;

import com.urise.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() throws ClassNotFoundException {
        super(Config.get().getSqlStorage());
    }
}