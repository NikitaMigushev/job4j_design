package ru.job4j.set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenContainsValue() {
        Set<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        assertThat(set.contains("two")).isTrue();
    }

    @Test
    void whenNotContainsValue() {
        Set<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        assertThat(set.contains("fout")).isFalse();
    }

    @Test
    void whenContainsNull() {
        Set<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add(null);
        assertThat(set.contains(null)).isTrue();
    }

    @Test
    void whenNotContainsNull() {
        Set<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        assertThat(set.contains(null)).isFalse();
    }
}