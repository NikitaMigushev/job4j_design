package ru.job4j.ood.controlq;

public class Warehouse extends AbstractStore {
    @Override
    public boolean accept(Food food, double freshnessPercentage) {
        boolean result = false;
        if (freshnessPercentage < 25) {
            result = true;
        }
        return result;
    }
}
