package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        List<String> list = new ArrayList<>();
        list.add("four");
        list.add("five");
        rsl.add(1, "four");
        rsl.addAll(list);
        List<String> listAddByIndex = new ArrayList<>();
        listAddByIndex.add("six");
        listAddByIndex.add("seven");
        rsl.addAll(2, listAddByIndex);
        List<String> listOf = List.of("eight", "nine", "ten");
        rsl.addAll(listOf);
        rsl.set(1, "two and second");
        rsl.replaceAll(String::toUpperCase);
        rsl.remove(1);
        List<String> removeAllList = new ArrayList<>();
        removeAllList.add("one");
        removeAllList.add("three");
        rsl.removeAll(list);
        for (String s : rsl) {
            System.out.println("Текущий элемент: " + s);
        }
        for (int i = 0; i < rsl.size(); i++) {
            System.out.println("Текущий элемент: " + rsl.get(i));
        }
        Iterator<String> iterator = rsl.iterator();
        while (iterator.hasNext()) {
            System.out.println("Текущий элемент: " + iterator.next());
        }
        ListIterator<String> listIterator = rsl.listIterator();
        while (listIterator.hasNext()) {
            System.out.println("Текущий элемент: " + listIterator.next());
        }

    }
}
