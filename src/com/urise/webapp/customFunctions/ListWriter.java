package com.urise.webapp.customFunctions;

import java.io.IOException;

@FunctionalInterface
public interface ListWriter<T> {
    void accept(T t) throws IOException;
}
