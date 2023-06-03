package ru.job4j.ood.controlq;

public class Trash extends AbstractStore {
    @Override
    public boolean accept(Food food, double freshnessPercentage) {
        boolean result = false;
        if (freshnessPercentage == 100) {
            result = true;
        }
        return result;
    }

    @Override
    public void addFood(Food food) {
    }
}
