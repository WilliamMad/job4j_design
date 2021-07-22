package ru.job4j.generics;

import java.util.*;
import java.util.stream.IntStream;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findByIndexById(id) != -1) {
            mem.set(Integer.parseInt(id), model);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (findByIndexById(id) != -1) {
            mem.remove(findByIndexById(id));
        }
        return false;
    }

    @Override
    public T findById(String id) {
        return mem.get(Integer.parseInt(id));
    }

    @Override
    public int findByIndexById(String id) {
        return IntStream.range(0, mem.size())
                .filter(i -> id.equals(mem.get(i).getId()))
                .findFirst()
                .orElse(-1);
    }
}
