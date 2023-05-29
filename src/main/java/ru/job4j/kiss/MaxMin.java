package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> values, Comparator<T> comparator) {
        return findExtremeValue(values, comparator, (current, extreme) -> comparator.compare(current, extreme) > 0);
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        return findExtremeValue(values, comparator, (current, extreme) -> comparator.compare(current, extreme) < 0);
    }

    private <T> T findExtremeValue(List<T> values, Comparator<T> comparator, BiPredicate<T, T> condition) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }

        T extreme = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            T current = values.get(i);
            if (condition.test(current, extreme)) {
                extreme = current;
            }
        }
        return extreme;
    }
    @FunctionalInterface
    private interface BiPredicate<T, U> {
        boolean test(T t, U u);
    }
}
