package com.urise.webapp.storage;

import com.urise.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(new SqlStorage(Config.get().dbUrl(), Config.get().dbUser(), Config.get().dbPassword()));
    }
}