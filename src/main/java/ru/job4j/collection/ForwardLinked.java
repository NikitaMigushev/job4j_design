package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    /**
     * size - number of elements in Node List.
     */
    private int size = 0;

    /**
     * modCount - internal flag to know the status of the collection, whether the collection is
     * structurally modified or not. The modCount flag is updated each time a collection is modified;
     * it checks the next value; if it finds, then the modCount will be modified after this
     * iterator has been created. It will throw ConcurrentModificationException.
     */
    private int modCount = 0;

    /**
     * head - first Node in Node List.
     */
    private Node<T> head;

    /**
     * Method adds Node into Node List by recording reference to it in the previous node.
     * If this is the first node, it becomes head.
     */
    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;

        }
        size++;
        modCount++;
    }
    
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value, head);
        head = newNode;
    }

    /**
     * Method returns Node by its index by iterating though Node reference from head to index
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    /**
     * * Iterator iterates through NodeList by iterating through Node reference.
     * It implemets Fail-Fast behavior.
     */

    public T deleteFirst() {
        T rsl = null;
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            rsl = head.item;
            Node<T> newHead = head.next;
            head.next = null;
            head.item = null;
            head = newHead;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    public static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
