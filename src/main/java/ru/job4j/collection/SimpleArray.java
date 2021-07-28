package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size;
    private int modCount;

    public SimpleArray() {
        container = new Object[size];
    }

    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return (T) container[index];
    }

    public void add(T model) {
        Objects.checkIndex(size, container.length);
        container[size++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[current++];
            }
        };
    }
}
