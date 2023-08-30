package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T>  implements Iterator<T> {
    private List<T> data;
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return !data.isEmpty();
    }

    @Override
    public T next() {
        if (data.isEmpty()) {
            throw new NoSuchElementException("Iterator is empty");
        }
        if (index > data.size() - 1) {
            index = 0;
        }
        T element = data.get(index);
        index++;
        return element;
    }
}
