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
        int hashCode = (key == null) ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if ((float) count / (float) table.length >= LOAD_FACTOR) {
            expand();
            hashCode = (key == null) ? 0 : key.hashCode();
            index = indexFor(hash(hashCode));
        }
        if (table[index] != null) {
            rsl = false;
        } else {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            modCount++;
            count++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> table.length);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> el : table) {
            if (el != null) {
                int hashCode = el.key == null ? 0 : el.key.hashCode();
                int index = indexFor(hash(hashCode));
                newTable[index] = el;
            }
        }
        table = newTable;
    }

    @Override

    public V get(K key) {
        V rsl = null;
        int hashCode = key == null ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] != null) {
            K k = table[index].key;
            int kh = k == null ? 0 : k.hashCode();
            if (hashCode == kh && k == key || (key != null && key.equals(k))) {
                rsl = table[index].value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int hashCode = key == null ? 0 : key.hashCode();
        int index = indexFor(hash(hashCode));
        if (table[index] != null) {
            K k = table[index].key;
            int kh = k == null ? 0 : k.hashCode();
            if (hashCode == kh && k == key || (key != null && key.equals(k))) {
                table[index] = null;
                rsl = true;
                modCount++;
                count--;
            }
        }
        return rsl;
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
                K rsl = table[currentIndex].key;
                currentIndex++;
                return rsl;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
