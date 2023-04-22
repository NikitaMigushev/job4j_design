
package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    /**
     * Container - array of objects which is initialized with initial capacity.
     * container is dynamic, which means, if there are no empty slots left, a new copy of
     * array is created with doubled size.
     */
    private T[] container;
    /**
     * Size - number of elements in the array of objects. Increment when add element,
     * decrement when remove
     */
    private int size;
    /**
     * modCount - internal flag to know the status of the collection, whether the collection is
     * structurally modified or not. The modCount flag is updated each time a collection is modified;
     * it checks the next value; if it finds, then the modCount will be modified after this
     * iterator has been created. It will throw ConcurrentModificationException.
     */
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    /**
     * Method adds element to container.
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        modCount++;
        size++;
    }

    /**
     * Method finds element by index and them replaces it with new element, shifts all emenets to the left
     * and put null in the end of container.
     */
    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        container[index] = newValue;
        return rsl;
    }

    /**
     * Method finds elemnent by index, removes it with
     */
    @Override
    public T remove(int index) {
        modCount++;
        T rsl = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        return rsl;
    }

    /**
     * Method finds element by index and then returns it.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * Method returns number of elements added to container.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method doubles container's capacity by making a copy of it.
     */
    private T[] grow() {
        return Arrays.copyOf(container, container.length == 0 ? 1 : container.length * 2);
    }

    /**
     * Iterator iterates through container.
     * Iterator implemets Fail-Fast behavior.
     * Each operation that structurally modifies (adding and removing elements)
     * the collection must increment modCount.
     * In turn, the iterator remembers the value of this modCount at the time of its creation (expectedModCount),
     * and then at each iteration compares the stored value with the current value of the modCount field, if they differ,
     * an ConcurrentModificationException is generated.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int point = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }

    public static void main(String[] args) {
        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>(10);
        simpleArrayList.add(1);
        simpleArrayList.add(2);
        simpleArrayList.add(3);
        System.out.println(simpleArrayList.get(5));
    }
}
