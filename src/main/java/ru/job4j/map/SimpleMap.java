package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (1f * count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexForKey(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            modCount++;
            count++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int indexForKey(K key) {
        int hashCode = (key == null) ? 0 : key.hashCode();
        return indexFor(hash(hashCode));

    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> el : table) {
            if (el != null) {
                int index = indexForKey(el.key);
                newTable[index] = el;
            }
        }
        table = newTable;
    }

    @Override

    public V get(K key) {
        V rsl = null;
        int index = indexForKey(key);
        if (table[index] != null && compareKeys(key, table[index].key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexForKey(key);
        if (table[index] != null && compareKeys(key, table[index].key)) {
            table[index] = null;
            rsl = true;
            modCount++;
            count--;
        }
        return rsl;
    }

    /**
     * Method compares two keys by hashCode and then by equals
     *
     * @param key          - the key that we are looking for.
     * @param compareToKey - the key, that we want to compare with.
     * @return boolean
     */
    private boolean compareKeys(K key, K compareToKey) {
        int keyHashCode = key == null ? 0 : key.hashCode();
        int compareToKeyHashCode = key == null ? 0 : key.hashCode();
        return keyHashCode == compareToKeyHashCode && Objects.equals(key, compareToKey);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int currentIndex = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (currentIndex < capacity && table[currentIndex] == null) {
                    currentIndex++;
                }
                return currentIndex < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[currentIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
