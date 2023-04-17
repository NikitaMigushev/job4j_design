package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class EmptyIteratorUsage {
    public static void main(String[] args) {
        Iterator itr = Collections.emptyIterator();
        System.out.println(itr.hasNext());

        List<String> myList = new ArrayList<>();
        myList.add("foo");
        myList.add("bar");
        myList.add("baz");

        Iterator<String> cursor = myList.iterator();

        while (cursor.hasNext()) {
            String element = cursor.next();
            System.out.println(element);
        }

    }
}
