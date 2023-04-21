package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        T value = linked.deleteFirst();
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}
