package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMinUsage {
    public static void main(String[] args) {
        MaxMin maxMin = new MaxMin();
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");
        String longestName = maxMin.max(names, Comparator.comparingInt(String::length));
        System.out.println(longestName);
    }

}
