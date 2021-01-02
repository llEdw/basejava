package com.urise.webapp.customFunctions;

import java.io.IOException;

@FunctionalInterface
public interface MapWriter<K, V> {
    void accept(K k, V v) throws IOException;
}
