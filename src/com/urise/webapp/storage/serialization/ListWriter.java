package com.urise.webapp.storage.serialization;

import java.io.IOException;

@FunctionalInterface
public interface ListWriter<T> {
    void accept(T t) throws IOException;
}
