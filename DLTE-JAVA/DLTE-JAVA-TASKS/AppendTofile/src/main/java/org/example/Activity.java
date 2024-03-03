package org.example;

import java.io.IOException;

public interface Activity<T> {
    void create(T item) throws IOException;
}
