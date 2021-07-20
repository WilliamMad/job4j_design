package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size;

    public void add(T model) {
        Objects.checkIndex(size, container.length);
        container[size] = model;
        size++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        container[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[index] = null;
        size--;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) new Iterator<Object>() {
            @Override
            public boolean hasNext() {
                return container.length < size;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[size++];
            }
        };
    }
}
