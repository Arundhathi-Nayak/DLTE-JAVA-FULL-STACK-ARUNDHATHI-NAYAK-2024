package org.example;

public interface Activity<T> {
    void create(T index);
    T read(int index);
    void update(int index,T newItem);
    void delete(int index);
}
