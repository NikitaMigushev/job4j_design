package ru.job4j.ood.controlq;

public class Trash extends AbstractStore {
    @Override
    public boolean accept(Food food, double freshnessPercentage) {
        return freshnessPercentage == 100;
    }

    @Override
    public void addFood(Food food) {
    }
}
