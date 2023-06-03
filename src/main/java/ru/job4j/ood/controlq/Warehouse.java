package ru.job4j.ood.controlq;

public class Warehouse extends AbstractStore {
    @Override
    public boolean accept(Food food, double freshnessPercentage) {
        return freshnessPercentage < 25;
    }
}
