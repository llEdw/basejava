package com.urise.webapp.storage.serialization;

import java.io.IOException;

@FunctionalInterface
public interface CollectionReader {
    void accept() throws IOException;
}