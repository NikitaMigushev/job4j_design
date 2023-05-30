package ru.job4j.template;

import java.util.Map;

public interface Generator<K, V> {
    String produce(String template, Map<K, V> args);
}
