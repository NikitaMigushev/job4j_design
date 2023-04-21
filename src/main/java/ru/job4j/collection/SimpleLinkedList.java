package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
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
    private Node<E> head;

    /**
     * Method adds Node into Node List by recording reference to it in the previous node.
     * If this is the first node, it becomes head.
     */
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;

        }
        size++;
        modCount++;
    }

    /**
     * Method returns Node by its index by iterating though Node reference from head to index
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    /**
     * * Iterator iterates through NodeList by iterating through Node reference.
     * It implemets Fail-Fast behavior.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    public static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.size);
    }
}