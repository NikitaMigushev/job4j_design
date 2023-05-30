package ru.job4j.template;

import java.util.Map;

public class SimpleGenerator<K, V> implements Generator<K, V> {

    @Override
    public String produce (String template, Map<K, V> args) {
        return null;
    }
}
